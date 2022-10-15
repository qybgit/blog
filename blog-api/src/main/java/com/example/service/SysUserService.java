package com.example.service;

import com.example.dao.popj.SysUser;
import com.example.vo.Result;
import com.example.vo.UserVo;

public interface SysUserService {
    SysUser findAuthorById(long authorId);

    UserVo findUserVoById(long authorId);

    Result getUsersInfo(String token);

    SysUser selectByAccount(String account);

    void save(SysUser sysUser);

//    UserVo findToUSer(long user_id);
}
