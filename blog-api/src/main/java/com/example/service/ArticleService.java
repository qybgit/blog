package com.example.service;

import com.example.vo.Result;
import com.example.vo.params.ArticleParam;
import com.example.vo.params.PageParams;

public interface ArticleService {
    Result listArticle(PageParams params);

    Result hotArticle();

    Result newArticles();

    Result listArchives();

    Result findArticleById(Long id);

    Result publish(ArticleParam articleParam);

    Result findUserArticle();

    Result findCount();
}
