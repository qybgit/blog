package com.example.dao.popj;

import lombok.Data;

@Data
public class ArticleBody {
    private Long id;
    private String content;
    private String contentHtml;
    private Long articleId;
}
