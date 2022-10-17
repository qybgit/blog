package com.example.dao.mapper;

import com.example.dao.popj.Article;
import com.example.dao.popj.ArticleBody;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ArticleMapper {
    /**
     * 查找所有文章
     * @return
     */
    @Select("select * from ms_article order by weight desc,create_date")
    List<Article> selectArticle();
    @Select("select * from ms_article where category_id=#{id} order by weight desc,create_date")
    List<Article> selectArticle1(long id);

//    @Select("select * from ms_article where =#{id} order by weight desc,create_date")
//    List<Article> selectArticle2(long id);//先拿标签articleId通过id
    @Select("select article_id from ms_article_tag where tag_id=#{id}")
    List<Long> articleIdList(long id);

    /**
     * 查找所有文章改进版
     * @return
     */
    @Select("<script>"
            +" select * from ms_article" +
            "<where>"
            + "<if test='categoryId!=null'>" +
            "and category_id=#{categoryId}" +
            "</if>" +
            "<if test='tagId!=null'>" +
            "and id in (select article_id from ms_article_tag where tag_id=#{tagId})" +
            "</if>" +
            "<if test='year.length >0 and year!=null and month.length>0 and month!=null '>" +
            "and YEAR(FROM_UNIXTIME(create_date/1000))=#{year} and MONTH(FROM_UNIXTIME(create_date/1000))=#{month} " +
            "</if>" +
            "</where>" +
            "order by weight desc,create_date" +
            "</script>")
    List<Article> selectArticlePlus(Long categoryId,Long tagId,String year,String month);
    /**
     * 最热的limit个文章
     * @param limit
     * @return
     */
    @Select("select * from ms_article order by view_counts desc limit ${limit}")
    List<Article> selectHotArticle(int limit);

    /**
     * 最新limit个文章
     * @param limit
     * @return
     */
    @Select("select * from ms_article order by create_date desc limit ${limit}")
    List<Article> selectnewArticle(int limit);
    @Select("select create_date from ms_article where id=${id}")
    long create(int id);

    /**
     * 通过id查找文章
     * @param id
     * @return
     */
    @Select("select * from ms_article where id=#{id}")
    Article findArticleById(Long id);

    /**
     * 通过Id查找文章body
     * @param id
     * @return
     */
    @Select("select * from ms_article_body where id=#{id}")
    ArticleBody findArticleBody(Long id);

    /**
     * 插入文章
     * @param article
     * @return
     */
    @Insert("insert into ms_article(id,comment_counts,create_date,summary,title,view_counts,weight,author_id,body_id,category_id)" +
            "values(#{id},#{comment_Counts},#{create_Date},#{summary},#{title},#{view_Counts},#{weight},#{author_Id},#{body_Id},#{category_Id})")
    @Options(useGeneratedKeys = true,keyProperty = "id")
    long insertArticle(Article article);

    /**
     * 插入文章body
     * @param articleBody
     * @return
     */
    @Insert("insert into ms_article_body(id,content,content_html,article_id) values(#{id},#{content},#{contentHtml},#{articleId})")
    @Options(useGeneratedKeys = true,keyProperty = "id")
    int insertArticleBody(ArticleBody articleBody);

    /**
     * 更新文章内容
     * @param article
     * @return
     */
    @Update("update ms_article set comment_counts=#{comment_Counts},create_date=#{create_Date},summary=#{summary},title=#{title},view_counts=${view_Counts},weight=${weight},author_id=${author_Id},body_id=${body_Id},category_id=${category_Id}" +
            " where id=#{id} ")
    int updateArticle(Article article);

    /**
     * 根据作者id查找文章
     * @return
     */
    @Select("select * from ms_article where author_id=#{id}")
    List<Article> findUserArticle(Long id);

    /**
     * 查找文章数量
     * @return
     */
    @Select("select count(*) from ms_article ")
    Long findCount();
}
