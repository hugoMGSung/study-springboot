package com.hugo83.stdmng.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.hugo83.stdmng.repository.StudentRepository;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StudentController {
	@Autowired
	private StudentRepository studentRepository;

	@GetMapping("/home")
	public String homePage(Model mv) {
		mv.addAttribute("studentlist", studentRepository.findAll());
		return "home";
	}
}
