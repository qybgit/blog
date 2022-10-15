package com.example.service.impl;

import com.example.dao.mapper.SysUserMapper;
import com.example.dao.popj.SysUser;
import com.example.service.LoginService;
import com.example.service.SysUserService;
import com.example.vo.ErrorCode;
import com.example.vo.LoginVo;
import com.example.vo.Result;
import com.example.vo.UserVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
@Service
public class SysUserServiceimpl implements SysUserService {
    @Resource
    SysUserMapper sysUserMapper;
    @Autowired
    LoginService loginService;

    /**
     * 通过authorId查找作者
     * @param authorId
     * @return
     */
    @Override
    public SysUser findAuthorById(long authorId) {
       SysUser sysUser= sysUserMapper.selectUserById(authorId);
        return sysUser;
    }
    @Override
    public UserVo findUserVoById(long authorId) {
       SysUser sysUser= sysUserMapper.selectUserById(authorId);
       UserVo userVo=new UserVo();
       BeanUtils.copyProperties(sysUser,userVo);
        return userVo;
    }

//    @Override
//    public UserVo findToUSer(long user_id) {
//        SysUser sysUser= sysUserMapper.selectToUserById(user_id);
//        UserVo userVo=new UserVo();
//        BeanUtils.copyProperties(sysUser,userVo);
//        return userVo;
//    }
    /**
     * 登陆获取用户信息
     * @param token
     * @return
     */
    @Override
    public Result getUsersInfo(String token) {
        SysUser sysUser=loginService.checkToken(token);
        if (sysUser==null)
            return Result.fail(ErrorCode.Token_Not.getCode(), ErrorCode.Token_Not.getMsg());
        LoginVo loginVo=new LoginVo();
        BeanUtils.copyProperties(sysUser,loginVo);
        return Result.success(loginVo);
    }

    @Override
    public SysUser selectByAccount(String account) {
        SysUser sysUser=sysUserMapper.selectByAccount(account);
        return sysUser;
    }

    /**
     * 插入注册表信息
     * @param sysUser
     */
    @Override
    public void save(SysUser sysUser) {
        sysUserMapper.insertUser(sysUser);
    }


}
