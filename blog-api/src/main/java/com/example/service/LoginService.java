package com.example.service;

import com.example.dao.popj.SysUser;
import com.example.vo.Result;
import com.example.vo.params.LoginPagParams;
import org.springframework.stereotype.Service;


public interface LoginService {

    Result login(LoginPagParams loginPagParams);

    SysUser checkToken(String token);

    Result loginOut(String token);

    Result register(LoginPagParams loginPagParams);
}
