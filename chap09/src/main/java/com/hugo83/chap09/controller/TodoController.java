package com.hugo83.chap09.controller;

import com.hugo83.chap09.dto.TodoDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/todo")
@Log4j2
public class TodoController {
    @RequestMapping("/list")
    public void list() {
        log.info("todo list.......");
    }

    //@RequestMapping(value = "/register", method = RequestMethod.GET)
    @GetMapping(value = "/register")
    public void registerGET() {
        log.info("GET Todo register........");
    }

    @PostMapping(value = "/register")
    public void registerPOST(TodoDTO todoDTO) {
        log.info("POST Todo register........");
        log.info(todoDTO);
    }
}
