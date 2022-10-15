package com.example.vo;

import lombok.AllArgsConstructor;
import lombok.Data;



public enum ErrorCode {
    PARAMS_ERROR(10001,"参数有误"),
    ACCOUNT_PWD_NOT_EXIST(10002,"用户名或密码不存在"),
    NO_PERMISSION(70001,"无访问权限"),
    SESSION_TIME_OUT(90001,"会话超时"),
    NO_LOGIN(90002,"未登录"),
    Token_Not(5000,"Token有误"),
    ACCOUNT_EXIST(50001,"参数已存在");
    private int code;
    private String msg;

    public int getCode(){
        return code;
    }
    public String getMsg(){
        return msg;
    }
    public void setCode(int code){
        this.code=code;
    }
    public void setMsg(String msg){
        this.msg=msg;
    }

    ErrorCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
