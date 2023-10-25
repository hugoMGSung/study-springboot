package com.hug83.chap09.controller;

import java.time.LocalDate;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hug83.chap09.dto.TodoDTO;

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

	@GetMapping("/ex4")
	public void ex4(Model model) {
		log.info("----------------------");
		model.addAttribute("message", "Hello World");
	}

	@GetMapping(value = "/ex4_1")
	public void ex4Extra(@ModelAttribute("dto") TodoDTO todoDTO, Model model) {
		log.info(todoDTO);
	}

	@GetMapping(value = "/ex5")
	public String ex5(RedirectAttributes redirectAttributes) {
		redirectAttributes.addAttribute("name", "ABC");
		redirectAttributes.addFlashAttribute("result", "success");

		return "redirect:/ex6";
	}

	@GetMapping(value = "/ex6")
	public void ex6() {
	}
}
