package com.example.comtrloler;

import com.example.service.LoginService;
import com.example.service.impl.LoginServiceimpl;
import com.example.vo.Result;
import com.example.vo.params.LoginPagParams;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("login")
public class LoginController {

    @Resource
    LoginServiceimpl service;

    /**
     * 登陆验证
     * @param loginPagParams
     * @return
     */
    @PostMapping
    public Result login(@RequestBody LoginPagParams loginPagParams){
        return service.login(loginPagParams);
    }

}
