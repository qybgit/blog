package com.example;

import com.alibaba.fastjson2.JSON;
import com.example.common.app.LogAnnotation;
import com.example.dao.mapper.ArticleMapper;
import com.example.dao.mapper.CommentMapper;
import com.example.dao.mapper.SysUserMapper;
import com.example.dao.mapper.TagMapper;
import com.example.dao.popj.*;
import com.example.service.LoginService;
import com.example.service.impl.ArticleServiceimpl;
import com.example.service.impl.LoginServiceimpl;
import com.example.service.impl.SysUserServiceimpl;
import com.example.service.impl.TagServiceimpl;
import com.example.util.UserThreadLocal;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@SpringBootTest
class BlogApiApplicationTests {
@Resource
    ArticleMapper mapper;
@Resource
    TagMapper tagMapper;
@Resource
    TagServiceimpl tagService;
@Resource
    SysUserMapper sysUserMapper;
@Resource
    SysUserServiceimpl sysUserServiceimpl;
@Resource
LoginService loginService;
@Resource
    ArticleServiceimpl articleServiceimpl;
@Resource
    RedisTemplate<String,String> template;
@Resource
    CommentMapper commentMapper;

    @Test

 void test(){
//        BCryptPasswordEncoder bcp = new BCryptPasswordEncoder();
//        String mm_pub = "123456";
//        String mm_encode = bcp.encode(mm_pub);
//        //bcp.matches(mm_pub,mm_encode)，第一个参数是前端传递过来的明文密码，如123456，第二个参数是添加用户时存储的密码
//        if(bcp.matches(mm_pub,mm_encode)){
//            System.out.println("密码校验成功");
//        }else{
//            System.out.println("密码错误");
//        }
//        String s = UUID.randomUUID().toString() + "." + StringUtils.substringAfterLast("1.png", ".");
//        System.out.println(s);


    }
    @Test

    void test1(){
        System.out.println("srg");
    }
}
