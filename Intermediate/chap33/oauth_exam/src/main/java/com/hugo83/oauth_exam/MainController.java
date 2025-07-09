package com.hugo83.oauth_exam;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@ResponseBody
public class MainController {

    @GetMapping("/")
    public String mainPage() {
        return "main";
    }
    
}
