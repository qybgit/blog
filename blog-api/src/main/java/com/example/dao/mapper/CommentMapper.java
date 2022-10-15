package com.example.dao.mapper;

import com.example.dao.popj.Comment;
import com.example.vo.params.CommentParam;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface CommentMapper {
    /**
     * 查找评论
     * @param article_id
     * @return
     */
    @Select("Select * from ms_comment where article_id=#{article_id}")
    List<Comment> findCommentByArticleId(long article_id);

    /**
     * 根据parentId查找子评论
     * @param id
     * @return
     */
    @Select("select * from ms_comment where parent_id=#{id}")
    List<Comment> findCommentById(Long id);

    /**
     * 写评论
     * @param comment
     * @return
     */
    @Insert("insert into ms_comment(id,content,create_date,article_id,author_id,parent_id,to_uid,level) values(#{id},#{content},#{create_Date},#{article_Id},#{author_Id},#{parent_Id},#{to_Uid},#{level}) ")
    int insertComment(Comment comment);
}
