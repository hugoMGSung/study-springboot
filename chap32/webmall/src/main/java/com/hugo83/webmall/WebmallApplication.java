package com.hugo83.webmall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class WebmallApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebmallApplication.class, args);
	}

	@GetMapping(value = "/")
	public String Home() {
		return "Hello, Spring Boot!";
	}

}
