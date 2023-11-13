package com.hugo83.appec2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/sample")
public class SampleController {
	@GetMapping(value = "/getAttr")
	public String[] getAttr() {
		return new String[] { "AAA", "BBB", "CCC" };
	}
}
