package com.example.dao.mapper;

import com.example.dao.popj.ArticleTag;
import com.example.dao.popj.Tag;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TagMapper {
    /**
     * 通过文章id查找对应标签
     * @param article_id
     * @return
     */
    @Select("select * from ms_tag where id in (select tag_id from ms_article_tag where article_id=${article_id})")
    List<Tag> selectTagByArticle(long article_id);

    /**
     * 查找最热标签id
     * @param limit
     * @return
     */
    @Select("select tag_id from ms_article_tag group by tag_id order by count(*) desc limit ${limit}")
    List<Long> selectHosTagId(int limit);

    /**
     * 查找最热标签
     * @param hotTagId
     * @return
     */
    @Select(

            "<script>" +
            " select * " +
            " from ms_tag where id in" +
            " <foreach collection='hotTagId' open='(' item='id' separator=',' close=')'>${id}</foreach> " +
            " </script>"
    )
    List<Tag> selectTagById(List<Long> hotTagId);

    /**
     * 查找所有标签
     * @return
     */
    @Select("select id,tag_name from ms_tag ")
    List<Tag> selectTagAll();

    /**
     * 查找所有标签详细
     * @return
     */
    @Select("select * from ms_tag ")
    List<Tag> selectTagDetail();

    /**
     * 插入articleTag信息
     * @param articleTag
     */
    @Insert("insert into ms_article_tag(id,article_id,tag_id) values(#{id},#{article_Id},#{tag_Id})")
    int insertArticle(ArticleTag articleTag);

    /**
     * 查找标签通过id
     * @param id
     * @return
     */
    @Select("select * from ms_tag where id=#{id}")
    Tag selectTagDetailById(long id);
}
