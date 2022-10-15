package com.example.dao.popj;

import lombok.Data;

/**
 *
 * 文章实体类
 */
@Data
public class Article {
    public static int Article_Common=0;
    public static int Article_Top=1;
    private Long id;

    private String title;

    private String summary;

    private int comment_Counts;

    private Long view_Counts;

    /**
     * 作者id
     */
    private Long author_Id;
    /**
     * 内容id
     */
    private Long body_Id;
    /**
     *类别id
     */
    private Long category_Id;

    /**
     * 置顶
     */
    private Integer weight ;


    /**
     * 创建时间
     */
    private Long create_Date;
}
