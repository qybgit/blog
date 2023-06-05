package com.example.controller;

import com.example.service.impl.TagServiceimpl;
import com.example.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("tag")
public class TagController {
    /**
     * 标签
     */
    @Resource
    TagServiceimpl tagService;

    /**
     * 最热标签
     * @return
     */
    @GetMapping("hot")
    public Result hotTag(){
        return tagService.findHotTag();
    }
    /**
     * 所有标签
     */
    @RequestMapping("tags")
    public Result allTag(){
        return tagService.findAllTag();
    }

    /**
     * 查找所有标签详细
     * @return
     */

    @GetMapping("detail")
    public Result detail(){
        return tagService.findAllTagDetail();
    }
    /**
     * 查找所有标签通过id
     *
     */
    @PostMapping("detail/{id}")
    public Result detailById(@PathVariable("id") long id){
    return tagService.finTagById(id);
    }
}
