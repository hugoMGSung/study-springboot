package com.hugo83.b01.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hugo83.b01.dto.MemberJoinDTO;
import com.hugo83.b01.service.MemberService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/member")
@Log4j2
@RequiredArgsConstructor
public class MemberController {
	private final MemberService memberService;

	@GetMapping("/join")
	public void joinGET() {
		log.info("join get...");
	}

	// @PostMapping("/join")
	// public String joinPOST(MemberJoinDTO memberJoinDTO) {
	// log.info("join post...");
	// log.info(memberJoinDTO);

	// return "redirect:/board/list";
	// }

	@PostMapping("/join")
	public String joinPOST(MemberJoinDTO memberJoinDTO, RedirectAttributes redirectAttributes) {
		log.info("join post...");
		log.info(memberJoinDTO);

		try {
			memberService.join(memberJoinDTO);
		} catch (MemberService.MidExistException e) {

			redirectAttributes.addFlashAttribute("error", "mid");
			return "redirect:/member/join";
		}

		redirectAttributes.addFlashAttribute("result", "success");

		return "redirect:/member/login"; // 회원 가입 후 로그인
	}

	@GetMapping("/login")
	public void loginGET(String error, String logout) {
		log.info("login get..............");
		log.info("logout: " + logout);

		if (logout != null) {
			log.info("user logout............");
		}
	}

	@GetMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		new SecurityContextLogoutHandler().logout(request, response,
				SecurityContextHolder.getContext().getAuthentication());
		return "redirect:/member/login";
	}
}
