package com.hugo83.boardback.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Log4j2
public class SampleController {
	@GetMapping("/hello")
	public void getHello(Model model) {
		log.info("Hello controller!");
		model.addAttribute("message", "SpringBoot Thymeleaf!");
	}
}
