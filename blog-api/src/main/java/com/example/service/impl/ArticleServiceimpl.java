package com.example.service.impl;

import com.alibaba.fastjson2.JSON;
import com.example.common.app.LogAnnotation;
import com.example.dao.mapper.ArchivesMapper;
import com.example.dao.mapper.ArticleMapper;
import com.example.dao.popj.*;
import com.example.service.ArticleService;
import com.example.service.CategoryService;
import com.example.service.ThreadService;
import com.example.util.UserThreadLocal;
import com.example.vo.*;
import com.example.vo.params.ArticleParam;
import com.example.vo.params.PageParams;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleServiceimpl implements ArticleService {
    @Resource
    ArticleMapper mapper;
    @Resource
    TagServiceimpl tagServiceimpl;
    @Resource
    SysUserServiceimpl sysUserServiceimpl;
    @Resource
    ArchivesMapper archivesMapper;
    @Resource
    CategoryService categoryService;
    @Resource
    RedisTemplate<String,String> template;
    @Override
    public Result listArticle(PageParams params) {

        List<Article> articleList=new ArrayList<>();
        PageHelper.startPage(params.getPage(),params.getPageSize());
//        /**
//         * PageHelper 能给下面运行的sql语句插入limit
//         * 实现分页置顶排序
//         */
//        if (params.getTagId()!=null){
//            List<Long> articleIdList=mapper.articleIdList(params.getTagId());
//            for (Long id:articleIdList){
//                articleList.add(mapper.findArticleById(id));
//            }
//       }else
//        if (params.getCategoryId()!=null){
//            articleList=mapper.selectArticle1(params.getCategoryId());
//        }else {
//            articleList=mapper.selectArticle();
//        }
   articleList=mapper.selectArticlePlus(params.getCategoryId(),params.getTagId(),params.getYear(),params.getMonth());
//getTag为null程序编译失败
       PageInfo<Article> pageInfo=new PageInfo<>(articleList);
//        //包装查询到的数据
       List<ArticleVo> articleVoList=copyArticleList(pageInfo.getList(),true,true);
        return Result.success(articleVoList);
    }

    /**
     * 最热文章
     * @return
     */
    @Override
    @LogAnnotation(module = "最热文章",operator = "hot")
    public Result hotArticle() {
        int limit=3;
        List<Article> hotArticleList= mapper.selectHotArticle(limit);
        List<ArticleVo> hotArticleVo=copyArticleList(hotArticleList,false,false);
        return Result.success(hotArticleVo);
    }

    /**
     * 最新文章
     * @return
     */
    @Override
    public Result newArticles() {
        int limit=3;
        List<Article> hotArticleList= mapper.selectnewArticle(limit);
        List<ArticleVo> hotArticleVo=copyArticleList(hotArticleList,false,false);
        return Result.success(hotArticleVo);
    }

    /**
     * 文章归纳
     * @return
     */
    @Override
    public Result listArchives() {

        return Result.success(archivesMapper.selectArchives());
    }

    /**
     * 文章详情
     * @param id
     * @return
     */
    @Resource
    ThreadService threadService;//线性池服务
    @Override
    public Result findArticleById(Long id) {
        Article article=mapper.findArticleById(id);
        ArticleVo articleVo=copy(article,true,true,true,true);
        long article_id =articleVo.getId();
        String view_count=template.opsForValue().get("article_id"+article_id);
        if (view_count==null){
            threadService.updateView_count(article_id,template,articleVo.getView_Counts());
        }
        else articleVo.setView_Counts(template.opsForValue().increment("article_id"+article_id));
        //使用redis加载view_count
        return Result.success(articleVo);
    }

    /**
     * 添加文章
     * @param articleParam
     * @return
     */

    @Override
    @Transactional
    public Result publish(ArticleParam articleParam) {
        Article article=new Article();
        SysUser sysUser = UserThreadLocal.get();
        article.setWeight(Article.Article_Common);
        mapper.insertArticle(article);
        article.setAuthor_Id(sysUser.getId());
        article.setTitle(articleParam.getTitle());
        article.setCreate_Date(System.currentTimeMillis());
        article.setSummary(articleParam.getSummary());
        article.setView_Counts(0L);
        article.setCategory_Id(articleParam.getCategory().getId());
        //文章body
        ArticleBody articleBody=new ArticleBody();
        articleBody.setArticleId(article.getId());
        articleBody.setContent(articleParam.getBody().getContent());
        articleBody.setContentHtml(articleParam.getBody().getContentHtml());
        mapper.insertArticleBody(articleBody);
        article.setBody_Id(articleBody.getId());
        List<TagVo> tagVo=articleParam.getTags();
        if(tagVo!=null){
            for (TagVo tag : tagVo) {
                ArticleTag articleTag = new ArticleTag();
                articleTag.setArticle_Id(article.getId());
                articleTag.setTag_Id(tag.getId());
                tagServiceimpl.insertArticleTag(articleTag);
            }
        }
        mapper.updateArticle(article);


        return Result.success(article.getId());
    }

    private List<ArticleVo> copyArticleList(List<Article> list,boolean isTag,boolean isAuthor) {
        List<ArticleVo> articleVoList=new ArrayList<>();
        for (Article article : list) {
            articleVoList.add(copy(article,isTag,isAuthor,false,false));
        }
        return articleVoList;
    }

    private List<ArticleVo> copyArticleList(List<Article> list,boolean isTag,boolean isAuthor,boolean isBody,boolean isCategory) {
        List<ArticleVo> articleVoList=new ArrayList<>();
        for (Article article : list) {
            articleVoList.add(copy(article,isTag,isAuthor,isBody,isCategory));
        }
        return articleVoList;
    }

    private ArticleVo copy(Article article,boolean isTag,boolean isAuthor,boolean isBody,boolean isCategory){
        ArticleVo articleVo=new ArticleVo();
        BeanUtils.copyProperties(article,articleVo);
        articleVo.setCreate_Date(new DateTime(article.getCreate_Date()).toString("yyyy-MM-dd HH:mm"));

        /**
         * BeanUtil为spring提供的一个工具类用于复制属性
         * 将时期转换为String类型，用joda框架
         */
        if(isTag){
            long article_id=articleVo.getId();
            articleVo.setTags(tagServiceimpl.findTageByArticle(article_id));
        }
        /**
         * 判断是否标签
         */
        if (isAuthor){
            long author_id=article.getAuthor_Id();
            articleVo.setAuthor(sysUserServiceimpl.findAuthorById(author_id).getNickname());
        }
        /**
         * 加载文章类容
         */
        if (isBody){
            ArticleBodyVo articleBodyVo=findArticleBody(article.getId());
            articleVo.setBody(articleBodyVo);
        }
        /**
         * 分类
         */
        if (isCategory){
            CategoryVo categoryVo=findCategory(article.getCategory_Id());
            articleVo.setCategory(categoryVo);
        }

        return articleVo;
    }

    private ArticleBodyVo findArticleBody(Long id) {
        ArticleBody articleBody=mapper.findArticleBody(id);
        ArticleBodyVo articleBodyVo=new ArticleBodyVo();
        articleBodyVo.setContent(articleBody.getContent());
        return articleBodyVo;
    }


    public CategoryVo findCategory(Long category_id) {
        Category category=categoryService.findCategory(category_id);
        CategoryVo categoryVo=new CategoryVo();
        BeanUtils.copyProperties(category,categoryVo);
        return categoryVo;
    }
}
