package com.example.service.impl;

import com.example.dao.mapper.CommentMapper;
import com.example.dao.popj.Comment;
import com.example.dao.popj.SysUser;
import com.example.service.CommentService;
import com.example.service.SysUserService;
import com.example.util.UserThreadLocal;
import com.example.vo.CommentVo;
import com.example.vo.ErrorCode;
import com.example.vo.Result;
import com.example.vo.UserVo;
import com.example.vo.params.CommentParam;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service
public class CommentServiceimpl implements CommentService {
    @Resource
    CommentMapper commentMapper;
    @Resource
    SysUserService sysUserService;

    /**
     * 查找评论
     * @param article_id
     * @return
     */
    @Override
    public Result findComment(long article_id) {
        List<Comment> commentList=commentMapper.findCommentByArticleId(article_id);
        List<CommentVo> commentVoList=copyList(commentList);

        return Result.success(commentVoList);
    }

    @Override
    public Result createComment(CommentParam commentParam) {
//        if (commentParam.getParent()==null
//        ||commentParam.getArticleId()==null
//        || StringUtils.isBlank(commentParam.getContent()))
//            return Result.fail(ErrorCode.PARAMS_ERROR.getCode(),ErrorCode.PARAMS_ERROR.getMsg());
        SysUser sysUser= UserThreadLocal.get();
        Comment comment=new Comment();
        Long parent=commentParam.getParent();
        if (parent==null||parent==0){
            comment.setLevel(1);
        }else {
            comment.setLevel(2);
        }
        comment.setParent_Id(parent==null ? 0 :parent);//三元运算
        comment.setTo_Uid(commentParam.getToUserId());
        comment.setCreate_Date(System.currentTimeMillis());
        comment.setAuthor_Id(sysUser.getId());
        comment.setArticle_Id(commentParam.getArticleId());
        comment.setContent(commentParam.getContent());
        commentMapper.insertComment(comment);
        return Result.success("成功");
    }

    private List<CommentVo> copyList(List<Comment> commentList) {
        List<CommentVo> commentVoList=new ArrayList<>();
        for (Comment comment:commentList){
            commentVoList.add(copy(comment));
        }
        return commentVoList;
    }

    private CommentVo copy(Comment comment) {
        CommentVo commentVo=new CommentVo();
        BeanUtils.copyProperties(comment,commentVo);

        long article_id=comment.getArticle_Id();
        UserVo userVo=sysUserService.findUserVoById(article_id);
        commentVo.setAuthor(userVo);

        int level=comment.getLevel();
        if (level==1){
            long id=comment.getId();
            List<Comment> commentList=commentMapper.findCommentById(id);
            List<CommentVo> commentVoList=copyList(commentList);
            commentVo.setChildrens(commentVoList);
        }//子评论
        if (level>1){
            long user_id=comment.getTo_Uid();
            UserVo userVo1=sysUserService.findUserVoById(user_id);
            commentVo.setTo_User(userVo1);
        }else {
            commentVo.setTo_User(null);
        }

        return commentVo;
    }
}
