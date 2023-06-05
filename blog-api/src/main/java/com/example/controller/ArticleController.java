package com.example.controller;
import com.example.common.app.LogAnnotation;
import com.example.service.impl.ArticleServiceimpl;
import com.example.vo.Result;
import com.example.vo.params.ArticleParam;
import com.example.vo.params.PageParams;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@RestController
@RequestMapping("/article")

public class ArticleController {
    /**
     * 文章
     */
    @Resource
    ArticleServiceimpl service;
    /**
     * 文章列表
     * @param params
     * @return
     */
    @PostMapping("All")
    @LogAnnotation(module = "文章",operator = "查询所有文章")
    //自写注解，记录日志，aop日志
    public Result article(@RequestBody PageParams params){

       return service.listArticle(params);

    }
    /**
     * 最热标签
     * @return
     */
    @PostMapping("hot")
    @LogAnnotation(module = "文章",operator = "最热文章")
    public Result hotArticle(){
        return service.hotArticle();
    }

    /**
     * 最新标签
     * @return
     */
    @PostMapping("new")
    @LogAnnotation(module = "文章",operator = "最新标签")
    public Result newArticles(){
        return service.newArticles();
    }

    /**
     * 文章归纳
     * @return
     */
    @PostMapping("listArchives")
    @LogAnnotation(module = "文章",operator = "文章归纳")
    public Result listArchives(){
        return service.listArchives();
    }

    /**
     * 文章详情
     */
    @PostMapping("view/{id}")
    @LogAnnotation(module = "文章",operator = "文章详情")
    public Result findArticle(@PathVariable("id") Long id){
        return service.findArticleById(id);
    }
    /**
     * 写文章
     */
    @PostMapping("publish")
    @LogAnnotation(module = "文章",operator = "发布文章")
    public Result publish(@RequestBody ArticleParam articleParam){
        return service.publish(articleParam);
    }
    /**
     * 根据用户id查找文章
     */
    @PostMapping("userArticle")
    @LogAnnotation(module = "文章",operator = "用户所有文章")
    public Result userArticle(){
        return service.findUserArticle();
    }
    /**
     * 文章数量
     */
    @GetMapping("count")
    @LogAnnotation(module = "文章",operator = "文章数量")
    public Result count(){
        return service.findCount();
    }
}
