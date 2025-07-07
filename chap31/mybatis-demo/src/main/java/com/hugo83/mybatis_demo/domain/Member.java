package com.hugo83.mybatis_demo.domain;

import lombok.Data;

@Data
public class Member {
    private Long id;
    private String name;
    private String email;
    private String password;
}
