package com.hugo83.api01.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/sample")
public class SampleController {
	@Operation(summary = "Sample GET doA")
	@GetMapping("/doA")
	public List<String> doA() {
		return Arrays.asList("AAA", "BBB", "CCC");
	}
}
