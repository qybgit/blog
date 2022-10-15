package com.example.dao.popj;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class SysUser {
    private Long  id;

    private String account;

    private Integer admin;

    private String avatar;

    private Long create_Date;

    private Integer deleted;

    private String email;

    private Long last_Login;

    private String mobile_Phone_Number;

    private String nickname;

    private String password;

    private String salt;

    private String status;
}
