package com.example.controller;

import com.example.service.impl.SysUserServiceimpl;
import com.example.vo.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("users")
public class UsersController {
    @Resource
    SysUserServiceimpl sysUserService;

    /**
     * 登陆获取用户信息
     * @param token
     * @return
     */
    @GetMapping("currentUser")
    public Result currentUser(@RequestHeader("Authorization")String token){
        return sysUserService.getUsersInfo(token);
    }
}
