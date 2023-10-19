package com.hugo83.chap01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.web.bind.annotation.*;

@SpringBootApplication
public class Chap01Application {

	public static void main(String[] args) {
		SpringApplication.run(Chap01Application.class, args);
	}

}

@RestController
class Helloworld {
	@GetMapping("/")
	public String hello() {
		return "Hello World!";
	}
}