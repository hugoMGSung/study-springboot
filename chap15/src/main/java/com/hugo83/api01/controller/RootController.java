package com.hugo83.api01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/")
@Log4j2
@RequiredArgsConstructor
public class RootController {
	@GetMapping(value = "/")
	public void rootGet() {
		log.info("start /");
	}
}
