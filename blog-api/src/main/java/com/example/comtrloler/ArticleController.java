package com.example.comtrloler;
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

    //自写注解，记录日志，aop日志
    public Result article(@RequestBody PageParams params){

       return service.listArticle(params);

    }
    /**
     * 最热标签
     * @return
     */
    @PostMapping("hot")
    public Result hotArticle(){
        return service.hotArticle();
    }

    /**
     * 最新标签
     * @return
     */
    @PostMapping("new")
    public Result newArticles(){
        return service.newArticles();
    }

    /**
     * 文章归纳
     * @return
     */
    @PostMapping("listArchives")
    public Result listArchives(){
        return service.listArchives();
    }

    /**
     * 文章详情
     */
    @PostMapping("view/{id}")
    public Result findArticle(@PathVariable("id") Long id){
        return service.findArticleById(id);
    }
    /**
     * 写文章
     */
    @PostMapping("publish")
    public Result publish(@RequestBody ArticleParam articleParam){
        return service.publish(articleParam);
    }
}
