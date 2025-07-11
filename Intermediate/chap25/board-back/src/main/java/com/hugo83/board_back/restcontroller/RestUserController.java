package com.hugo83.board_back.restcontroller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hugo83.board_back.entity.SiteUser;
import com.hugo83.board_back.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
@Log4j2
public class RestUserController {
	
	private final UserService userService;

	
	@PostMapping("/user")
	public ResponseEntity<SiteUser> user(@RequestParam Map<String, String> data) {
		log.info(data);
		log.info("일반 인증");

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		//String username = auth.getName();
		String username = data.get("username");
		String password = data.get("password");

		SiteUser user = this.userService.getUserByUsernameAndPassword(username, password); 
		if (user != null) {
			return ResponseEntity.ok(user);
		} else {
			throw new UsernameNotFoundException("ERROR");
		}
		// 유저 정보
		// SiteUser user = userService.getUser(username); // 으음...

		
	}
	
}
