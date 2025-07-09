package com.hugo83.googlelogin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
// @ResponseBody
public class MainController {
    
    @GetMapping("/")
    public String mainPage() {
        return "main";
    }
    
}
