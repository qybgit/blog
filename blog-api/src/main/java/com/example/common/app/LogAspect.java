package com.example.common.app;

import com.alibaba.fastjson2.JSON;
import com.example.util.HttpContextUtils;
import com.example.util.IpUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

@Slf4j
@Component
@Aspect//切面 定义了通知和切点的关系
public class LogAspect {
//切点
    @Pointcut("@annotation(com.example.common.app.LogAnnotation)")
    public void pt(){
    }
    //环绕通知
    @Around("pt()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {

        long beginTime=System.currentTimeMillis();
        //执行方法
        Object result=joinPoint.proceed();
        long time=System.currentTimeMillis()-beginTime;
        recordLog(joinPoint,time);
        return result;

    }

    private void recordLog(ProceedingJoinPoint joinPoint, long time) {
        MethodSignature signature= (MethodSignature) joinPoint.getSignature();
        //获取签名
        Method method=signature.getMethod();
        //获取方法
        LogAnnotation logAnnotation=method.getAnnotation(LogAnnotation.class);
        //获取加了注解的方法
        log.info("=====================log start================================");
        log.info("module:{}",logAnnotation.module());
        log.info("operation:{}",logAnnotation.operator());
        //打印注解信息

        String className=joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        log.info("request method:{}",className + "." + methodName + "()");
        //请求的方法名

        Object[] args=joinPoint.getArgs();
        System.out.println(args.toString());
        String params = JSON.toJSONString(args);
        log.info("params:{}",params);
        //请求参数

        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        log.info("ip:{}", IpUtils.getIpAddr(request));
        //ip地址

        log.info("excute time : {} ms",time);
        log.info("=====================log end================================");
    }
}
