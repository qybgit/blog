package com.example.vo;

import lombok.Data;

import java.util.List;

@Data
public class CommentVo {

        private Long id;

        private UserVo author;

        private String content;

        private List<CommentVo> childrens;

        private String create_Date;

        private Integer level;

        private UserVo to_User;

}
