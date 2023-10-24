package com.hug83.chap09;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	@GetMapping("/")

	public String main() {
		System.out.println("home controller start");

		return "main";
	}
}
