package com.hug83.chap09.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hug83.chap09.dto.TodoDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/todo")
@Log4j2
@RequiredArgsConstructor
public class TodoController {
	@RequestMapping("/list")
	public void list(Model model) {
		log.info("todo list.......");
	}

	@GetMapping(value = "/register")
	// @RequestMapping(value = "/register", method = RequestMethod.GET)
	public void register() {
		log.info("GET todo register.......");
	}

	@PostMapping(value = "/register")
	public void registerPost(TodoDTO todoDTO) {
		log.info("POST todo 등록!.........");
		log.info(todoDTO);
	}
}
