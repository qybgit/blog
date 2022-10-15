package com.example.service;

import com.example.vo.Result;
import com.example.vo.params.CommentParam;

public interface CommentService {
    Result findComment(long article_id);

    Result createComment(CommentParam commentParam);
}
