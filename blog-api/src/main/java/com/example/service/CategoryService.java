package com.example.service;

import com.example.dao.popj.Category;
import com.example.vo.Result;

public interface CategoryService {

    Category findCategory(Long category_id);

    Result findCategoryAll();

    Result findCategoryDetail();

    Result findCategoryById(long id);
}
