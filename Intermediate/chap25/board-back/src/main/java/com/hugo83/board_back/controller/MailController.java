package com.hugo83.board_back.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.deser.std.StringArrayDeserializer;
import com.hugo83.board_back.entity.SiteUser;
import com.hugo83.board_back.service.MailService;
import com.hugo83.board_back.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RequestMapping("/email")
@RequiredArgsConstructor
@Controller
@Log4j2
public class MailController {

    private final UserService userService;

    private final MailService mailService;
	
	@GetMapping("/reset")
	public String reset_page() {
		return "mail/reset";
	}

	@PostMapping("/reset-password")
    public String reset_password(@RequestParam("email") String email) {
        JsonFactory factory = JsonFactory.builder().build();
        log.info(email);
        // db에서 이 메일을 확인한 뒤 없으면
        try {
            SiteUser user = this.userService.getUserByEmail(email);

            // 값이 있으니 이 메일로 초기화 메일을 보내자.
            mailService.sendResetPasswordEmail(email);

        } catch (Exception e) {
            return "ERROR";
        }

        return null;
    }
}
