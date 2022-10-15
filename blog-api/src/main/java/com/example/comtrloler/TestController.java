package com.example.comtrloler;

import com.example.vo.Result;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
public class TestController {

    @RequestMapping
    public Result test(@RequestHeader("Authorization")String token){

        return Result.success(1001);
    }
    @RequestMapping("a/{id}/b")
    public Result test2(@PathVariable("id")String token){

        return Result.success(token);
    }
    @RequestMapping("b")
    public Result test3(@RequestHeader("Authorization")String token){

        return Result.success(1003);
    }
}
