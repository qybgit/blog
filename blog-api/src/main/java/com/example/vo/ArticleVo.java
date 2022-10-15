package com.example.vo;

import lombok.Data;


import java.util.List;
@Data
public class ArticleVo {
    private Long id;

    private String title;

    private String summary;

    private int comment_Counts;

    private long view_Counts;

    private int weight;
    /**
     * 创建时间
     */
    private String create_Date;

    private String author;

    private ArticleBodyVo body;

    private List<TagVo> tags;

    private CategoryVo category;
}
