package com.example.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.aspectj.lang.annotation.Around;

@Data
@AllArgsConstructor
public class TokenVo {
    private String token;
    private String account;
}
