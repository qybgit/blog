package com.example.comtrloler;

import com.example.service.CategoryService;
import com.example.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("categorys")
public class CategoryController {
    @Resource
    CategoryService categoryService;
    /**
     * 查看分类
     */
    @RequestMapping
    public Result category(){
        return categoryService.findCategoryAll();
    }
    /**
     * 详细分类详细
     */
    @GetMapping("detail")
    public Result categoryDetail(){
        return categoryService.findCategoryDetail();
    }

    /**
     * 查找分类通过id
     * @param id
     * @return
     */
    @PostMapping("detail/{id}")
    public Result categoryById(@PathVariable("id") long id){
        return categoryService.findCategoryById(id);
    }
}
