package com.hugo83.board_back.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hugo83.board_back.service.UserService;
import com.hugo83.board_back.validation.UserForm;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/user")
@Controller
public class UserController {
	private final UserService userService;

	@GetMapping("/signup")
	public String signup(UserForm userForm) {
		return "user/signup";
	}

	@PostMapping("/signup")
	public String signup(@Valid UserForm userForm, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "user/signup";
		}

		if (!userForm.getPassword1().equals(userForm.getPassword2())) {
			bindingResult.rejectValue("password2", "passwordIncorrect", "패스워드가 일치하지 않습니다.");
			return "user/signup";
		}

		userService.setUser(userForm.getEmail(), userForm.getUsername(), userForm.getPassword1());

		return "redirect:/";
	}
}
