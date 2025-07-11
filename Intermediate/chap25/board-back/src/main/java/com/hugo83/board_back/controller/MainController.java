package com.hugo83.board_back.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
public class MainController {
	@GetMapping("/hello")
	@ResponseBody
	public String getHello() {
		log.info("Hello, SpringBoot!!");
		return "Hello, SpringBoot3!";
	}
	
	@GetMapping("/")
	public String index() {
		return "redirect:/board/list/freeboard";
	}
}
