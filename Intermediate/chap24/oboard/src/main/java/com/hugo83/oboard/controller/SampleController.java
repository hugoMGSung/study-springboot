package com.hugo83.oboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Log4j2
public class SampleController {
	@GetMapping("/hello")
	public void hello(Model model) {
		String msg = "Hello, Hugo's SpringBoot~!";
		log.info(msg);

		model.addAttribute("msg", msg);
	}	
}
