package com.example.comtrloler;

import com.example.service.LoginService;
import com.example.vo.Result;
import com.example.vo.params.LoginPagParams;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("register")
public class RegisterController {

    @Resource
    LoginService loginService;
    @PostMapping
    public Result register(@RequestBody LoginPagParams loginPagParams){

        return loginService.register(loginPagParams);
    }
}
