package com.hugo83.webmall.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hugo83.webmall.dto.UserDto;


@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/user")
    public UserDto getUser() {
        UserDto user = new UserDto();
        user.setUsername("Hugo");
        user.setAge(45);
        
        return user;
    }
    
}
