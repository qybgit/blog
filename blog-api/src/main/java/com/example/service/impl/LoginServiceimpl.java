package com.example.service.impl;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.example.dao.mapper.SysUserMapper;
import com.example.dao.popj.SysUser;
import com.example.service.LoginService;
import com.example.service.SysUserService;
import com.example.util.JwtUtil;
import com.example.vo.ErrorCode;
import com.example.vo.Result;
import com.example.vo.params.LoginPagParams;
import io.jsonwebtoken.Jwt;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

@Service
public class LoginServiceimpl implements LoginService {

    @Resource
    SysUserMapper sysUserMapper;
    @Lazy
    @Autowired
    SysUserService sysUserService;
    @Resource
    RedisTemplate<String,String> template;

    String slat="qyb";//加密盐
    /**
     * 登陆验证
     * @param loginPagParams
     * @return
     */
    @Override
    public Result login(LoginPagParams loginPagParams) {
        if (StringUtils.isBlank(loginPagParams.getPassword())||StringUtils.isBlank(loginPagParams.getAccount())){
            return Result.fail(ErrorCode.ACCOUNT_PWD_NOT_EXIST.getCode(),ErrorCode.ACCOUNT_PWD_NOT_EXIST.getMsg());
        }
        //验证合法
        String pwd= DigestUtils.md2Hex(loginPagParams.getPassword()+slat);
        SysUser sysUser=sysUserMapper.selectUser(loginPagParams.getAccount(),pwd);
        if(sysUser!=null){
            String token=JwtUtil.createToken(sysUser.getId());
            template.opsForValue().set("Token_"+token, JSON.toJSONString(sysUser));
            return Result.success(token);

        }
        return Result.fail(000,"找不到");
    }

    /**
     * 登陆用户验证token里用户信息是否存在
     * @param token
     * @return
     */
    @Override
    public SysUser checkToken(String token) {
        if(StringUtils.isBlank(token))
            return null;
        Map<String,Object> objectMap=JwtUtil.checkToken(token);
        if (objectMap==null)
            return null;
        String userJson=template.opsForValue().get("Token_"+token);
        if(StringUtils.isBlank(userJson))
            return null;
        //是否合法
        SysUser sysUser=JSON.parseObject(userJson,SysUser.class);
        return sysUser;
    }

    /**
     * 退出登录
     * @param token
     * @return
     */
    @Override
    public Result loginOut(String token) {
        template.delete("Token_"+token);
        return Result.success(null);
    }

    /**
     * 注册
     * @param loginPagParams
     * @return
     */
    @Override
    public Result register(LoginPagParams loginPagParams) {
        String account=loginPagParams.getAccount();
        String password=loginPagParams.getPassword();
        String nice_name=loginPagParams.getNick_name();
        if(StringUtils.isBlank(account)||StringUtils.isBlank(password)||StringUtils.isBlank(nice_name))
            return Result.fail(ErrorCode.PARAMS_ERROR.getCode(), ErrorCode.PARAMS_ERROR.getMsg());

        SysUser sysUser=sysUserService.selectByAccount(account);
        if (sysUser!=null)
            return Result.fail(ErrorCode.ACCOUNT_EXIST.getCode(), ErrorCode.ACCOUNT_EXIST.getMsg());
        //参数验证
        sysUser=new SysUser();
        sysUser.setAccount(account);
        sysUser.setPassword(DigestUtils.md5Hex(password+slat));
        sysUser.setCreate_Date(System.currentTimeMillis());
        sysUser.setLast_Login(System.currentTimeMillis());
        sysUser.setAvatar("/static/img/logo.b3a48c0.png");
        sysUser.setAdmin(1); //1 为true
        sysUser.setDeleted(0); // 0 为false
        sysUser.setSalt(" 1");
        sysUser.setStatus(" 1");
        sysUser.setEmail(" no");
        sysUser.setNickname("张三");
        sysUser.setMobile_Phone_Number("");
        sysUserService.save(sysUser);
        String token=JwtUtil.createToken(sysUser.getId());
        template.opsForValue().set("Token_"+token, JSON.toJSONString(sysUser));
        return Result.success(token);

    }
}
