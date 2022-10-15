package com.example.dao.mapper;

import com.example.dao.popj.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CategoryMapper {
    /**
     * 通过category-id字段查找category
     * @param category_id
     * @return
     */
    @Select("select *from ms_category where id=#{id}")
    Category findCategory(Long category_id);

    /**
     * 查找所有标签
     * @return
     */
    @Select("select id,avatar, category_name from ms_category")
    List<Category> findCategoryAll();

    /**
     * 查找所有标签详细
     * @return
     */
    @Select("select * from ms_category")
    List<Category> findCategoryDetail();

    /**
     * 查找分类通过id
     * @param id
     * @return
     */
    @Select("select * from ms_category where id=#{id}")
    Category findCategoryById(long id);
}
