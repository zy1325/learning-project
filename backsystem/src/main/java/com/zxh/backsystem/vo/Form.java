package com.zxh.backsystem.vo;

import lombok.Data;

@Data
public class Form {
    private String username;
    private String password;
    private String token;
    private int code = 200;
}
