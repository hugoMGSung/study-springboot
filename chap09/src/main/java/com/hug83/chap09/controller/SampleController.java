package com.hug83.chap09.controller;

import java.time.LocalDate;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Log4j2
public class SampleController {
	@GetMapping("/hello")
	public void hello() {
		log.info("헬로로그!........");
	}

	@GetMapping(value = "/ex3")
	public void ex3(@RequestParam LocalDate dueDate) {
		log.info("ex3 테스트...........");
		log.info("dueDate : " + dueDate); // 현재는 오류가 나지 않음
	}

}
