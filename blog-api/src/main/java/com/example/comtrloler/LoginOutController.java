package com.example.comtrloler;

import com.example.service.LoginService;
import com.example.vo.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class LoginOutController {

    @Resource
    LoginService loginService;
    /**
     * 登出页面
     * @return
     */
    @GetMapping("loginout")
    public Result loginOut(@RequestHeader("Authorization")String token){
        return  loginService.loginOut(token);
    }
}
