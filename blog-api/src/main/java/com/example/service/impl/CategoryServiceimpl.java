package com.example.service.impl;

import com.example.dao.mapper.CategoryMapper;
import com.example.dao.popj.Category;
import com.example.service.CategoryService;
import com.example.vo.CategoryVo;
import com.example.vo.Result;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceimpl implements CategoryService {
    @Resource
    CategoryMapper categoryMapper;

    /**
     * 查找Category
     * @param category_id
     * @return
     */
    @Override
    public Category findCategory(Long category_id) {

        return categoryMapper.findCategory(category_id);
    }

    /**
     * 查找所有标签
     * @return
     */
    @Override
    public Result findCategoryAll() {
        List<Category> categoryList=categoryMapper.findCategoryAll();
        List<CategoryVo> categoryVoList=copyList(categoryList);
        return Result.success(categoryVoList);
    }

    /**
     * 查找分类详细
     * @return
     */
    @Override
    public Result findCategoryDetail() {
        List<Category> categoryList=categoryMapper.findCategoryDetail();
        List<CategoryVo> categoryVoList=copyList(categoryList);
        return Result.success(categoryVoList);
    }

    @Override
    public Result findCategoryById(long id) {
        Category category=categoryMapper.findCategoryById(id);

        return Result.success(copy(category));
    }

    private List<CategoryVo> copyList(List<Category> categoryList) {
        List<CategoryVo> categoryVoList=new ArrayList<>();
        for (Category category:categoryList){
            categoryVoList.add(copy(category));
        }
        return categoryVoList;
    }

    private CategoryVo copy(Category category) {
        CategoryVo categoryVo=new CategoryVo();
        BeanUtils.copyProperties(category,categoryVo);
        return categoryVo;
    }
}
