package com.example.service;

import com.example.dao.popj.ArticleTag;
import com.example.vo.Result;
import com.example.vo.TagVo;

import java.util.List;

public interface TagService {
    List<TagVo> findTageByArticle(long article_id);

    Result findHotTag();

    Result findAllTag();

    void insertArticleTag(ArticleTag articleTag);

    Result findAllTagDetail();

    Result finTagById(long id);
}
