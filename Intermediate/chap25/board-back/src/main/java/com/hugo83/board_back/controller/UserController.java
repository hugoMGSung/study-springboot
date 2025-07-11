package com.hugo83.board_back.controller;

import jakarta.validation.Valid;
import java.util.UUID;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;

import com.hugo83.board_back.common.SHA256Util;
import com.hugo83.board_back.config.MailConfig;
// import com.hugo83.board_back.service.BoardService;
// import com.hugo83.board_back.service.ReplyService;
import com.hugo83.board_back.service.UserService;
import com.hugo83.board_back.validation.UserCreateForm;


@RequiredArgsConstructor
@Controller
@RequestMapping("/user")
public class UserController {
	private final UserService userService;

	//private final BoardService boardService;
	// private final ReplyService replyService;

	private final MailConfig mailConfig;

	@GetMapping("/login")
	public String login() {
		return "user/login";
	}

	@GetMapping("/signup")
	public String signup(UserCreateForm userCreateForm) {
		return "user/signup";
	}
	
	@PostMapping("/signup")
	public String signup(@Valid UserCreateForm userCreateForm, BindingResult bindingResult) throws Exception {
		if (bindingResult.hasErrors()) {
			return "user/signup";
		}

		if (!userCreateForm.getPassword1().equals(userCreateForm.getPassword2())) {
			bindingResult.rejectValue("password2", "passwordInCorrect",
					"2개의 패스워드가 일치하지 않습니다.");
			return "user/signup";
		}

		try {
			userService.create(userCreateForm.getUsername(),
					userCreateForm.getEmail(), userCreateForm.getPassword1());
		} catch (DataIntegrityViolationException e) {
			e.printStackTrace();
			bindingResult.reject("signupFailed", "이미 등록된 사용자입니다.");
			return "user/signup";
		} catch (Exception e) {
			e.printStackTrace();
			bindingResult.reject("signupFailed", e.getMessage());
			return "user/signup";
		}

		//  SendMail(userCreateForm.getEmail()); // 지금 안됨 다시!!!
		return "redirect:/";
	}

	@PostMapping("/sendmail") // 
    @ResponseBody  //AJAX후 값을 리턴하기위해 작성
	public String SendMail(String email) throws Exception {
		UUID uuid = UUID.randomUUID(); // 랜덤한 UUID 생성
		String key = uuid.toString().substring(0, 7); // UUID 문자열 중 7자리만 사용하여 인증번호 생성
		String sub = "인증번호 입력을 위한 메일 전송";
		String con = "인증 번호 : " + key;
		mailConfig.send(email, sub, con);
		key = SHA256Util.getEncrypt(key, email);
		return key;
	}
	
	@PostMapping("/checkmail") 
    @ResponseBody  
	public boolean CheckMail(String key, String insertKey, String email) throws Exception {
		insertKey = SHA256Util.getEncrypt(insertKey, email);

		if (key.equals(insertKey)) {
			return true;
		}
		return false;
	}

	// @PreAuthorize("isAuthenticated()")
	// @GetMapping("/profile")
	// public String profile(Model model, Principal principal) {
	// 	String username = principal.getName();

	// 	model.addAttribute("username", username);
	// 	model.addAttribute("boardList", boardService.getCurrentListByUser(username, 5));

	// 	return "profile";
	// }
}
