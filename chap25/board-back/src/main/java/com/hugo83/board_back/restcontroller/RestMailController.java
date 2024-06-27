package com.hugo83.board_back.restcontroller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonFactory;
import com.hugo83.board_back.service.MailService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/mail")
@Log4j2
public class RestMailController {
    
    private final MailService mailService;

    @PostMapping("/test-email")
    @ResponseBody
    public ResponseEntity<HttpStatus> testEmail() {
        String to = "personar95@gmail.com";
        String subject = "SpringBoot 전송테스트";
        String message = "<h1>테스트 메일 메시지입니다</h1>";

        mailService.sendMail(to, subject, message);
        return new ResponseEntity<HttpStatus>(HttpStatus.OK);
    }

    
    
}