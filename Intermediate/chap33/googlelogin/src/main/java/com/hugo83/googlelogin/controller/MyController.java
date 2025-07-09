package com.hugo83.googlelogin.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MyController {

    @GetMapping("/my")
    @PreAuthorize("isAuthenticated()")
    public String myPage() {
        return "my";
    }
    
}
