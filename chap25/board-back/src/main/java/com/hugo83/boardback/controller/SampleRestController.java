package com.hugo83.boardback.controller;

import org.springframework.web.bind.annotation.RestController;

import com.hugo83.boardback.model.Test;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@RestController
@Log4j2
public class SampleRestController {
	@GetMapping("/helloJson")
	@ResponseBody
	public Test getHelloJson() {
		log.info("Hello Json!!");
		Test test = new Test();
		test.setKey("MyKey");
		test.setValue("MyValue");

		return test;
	}
}
