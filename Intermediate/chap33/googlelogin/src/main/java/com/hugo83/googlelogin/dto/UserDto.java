package com.hugo83.googlelogin.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private String username;
    private String name;
    private String role;
    //  private String email; // 나중에 필요할 지 몰라
}
