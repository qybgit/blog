package com.example.dao.popj;

import lombok.Data;

@Data
public class Comment {

    private Long id;

    private String content;

    private Long create_Date;

    private Long article_Id;

    private Long author_Id;

    private Long parent_Id;

    private Long to_Uid;

    private Integer level;
}
