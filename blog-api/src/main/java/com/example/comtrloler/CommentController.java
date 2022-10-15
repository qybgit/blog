package com.example.comtrloler;

import com.example.service.CommentService;
import com.example.vo.Result;
import com.example.vo.params.CommentParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("comments")
public class CommentController {
    @Resource
    CommentService commentService;

    /**
     * 查看文章所有评论
     * @param article_id
     * @return
     */
    @GetMapping("article/{id}")
    public Result comments(@PathVariable("id") long article_id){
        return Result.success(commentService.findComment(article_id));
    }
    /**
     * 写评论， 需登录
     */
    @PostMapping("create/change")
    public Result createComment(@RequestBody CommentParam commentParam){
        System.out.println(1);
        return commentService.createComment(commentParam);
    }
}
