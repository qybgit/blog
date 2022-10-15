package com.example.service.impl;

import com.example.dao.mapper.TagMapper;
import com.example.dao.popj.ArticleTag;
import com.example.dao.popj.Tag;
import com.example.service.TagService;
import com.example.vo.Result;
import com.example.vo.TagVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
@Service
public class TagServiceimpl implements TagService {
    @Resource
    TagMapper tagMapper;

    /**
     * 通过文章id查找标签
     * @param article_id
     * @return
     */
    @Override
    public List<TagVo> findTageByArticle(long article_id) {
        List<Tag> tagList=tagMapper.selectTagByArticle(article_id);
        List<TagVo> tagVoList=new ArrayList<>();
        for (Tag tag : tagList) {
            tagVoList.add(copy(tag));
        }
        return tagVoList;

    }

    /**
     * 查找最热的limit个标签
     * @return
     */
    @Override
    public Result findHotTag() {
        int limit=6;
        List<Long> hotTagId=tagMapper.selectHosTagId(limit);
        if (CollectionUtils.isEmpty(hotTagId)){
            return Result.success(Collections.emptyList());
        }
        List<Tag> hotTag=tagMapper.selectTagById(hotTagId);
        return Result.success(hotTag);
    }

    /**
     * 查找所有标签
     * @return
     */
    @Override
    public Result findAllTag() {
        List<Tag> tags=tagMapper.selectTagAll();
        List<TagVo> tagVoList=copyList(tags);
        return Result.success(tagVoList);
    }

    /**
     * 插入articleTag信息
     * @param articleTag
     */
    @Override
    public void insertArticleTag(ArticleTag articleTag) {
        tagMapper.insertArticle(articleTag);
    }

    /**
     * 查找所有标签详细
     * @return
     */
    @Override
    public Result findAllTagDetail() {
        List<Tag> tags=tagMapper.selectTagDetail();
        List<TagVo> tagVoList=copyList(tags);
        return Result.success(tagVoList);
    }

    /**
     * 查找标签通过id
     * @param id
     * @return
     */
    @Override
    public Result finTagById(long id) {
        Tag tag=tagMapper.selectTagDetailById(id);
        return Result.success(copy(tag));
    }

    private List<TagVo> copyList(List<Tag> tags) {
        List<TagVo> tagVoList=new ArrayList<>();
        for (Tag tag:tags){
            tagVoList.add(copy(tag));
        }
        return tagVoList;
    }

    private TagVo copy(Tag tag) {
        TagVo tagVo=new TagVo();
        BeanUtils.copyProperties(tag,tagVo);
        return tagVo;
    }
}
