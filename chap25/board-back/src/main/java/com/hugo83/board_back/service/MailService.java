package com.hugo83.board_back.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender javaMailSender;
    private final ResetService resetService;
    // private final PasswordEncoder passwordEncoder;

    @Value("${spring.mail.username}")
    private String from;

    private String makeUuid() {
        return UUID.randomUUID().toString();
    }

    public void sendMail(String to, String subject, String message) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage(); // MIME type 설정

        try {
            // MimeMessageHelper로 MimeMessage구성, 이메일에 작성되는 글은 UTF-8로 생성
            MimeMessageHelper mmh = new MimeMessageHelper(mimeMessage, true);
            // 이메일 수신자 설정
            mmh.setTo(to);
            // 이메일 제목 설정
            mmh.setSubject(subject);
            // 본문내용 설정
            mmh.setText(message, true);
            // 이메일 발신자 설정
            mmh.setFrom(new InternetAddress(from));
            // 이메일 전송
            javaMailSender.send(mimeMessage);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

	public String sendResetPasswordEmail(String email) {
        String uuid = makeUuid();
		String subject = "요청하신 비밀번호 재설정 입니다."; // 이메일 제목
		String message = "BackBoard" //html 형식으로 작성
			+ "<br><br>" + "아래 링크를 클릭하면 비밀번호 재설정 페이지로 이동합니다." + "<br>"
			+ "<a href=\"" + "http://localhost:8080/user/reset-password" + "/" + uuid + "\">"
			+ "http://localhost:8080/user/reset-password" + "/" + uuid + "</a>" + "<br><br>"
            + "<br>"; //이메일 내용 삽입
        sendMail(email, subject, message);

        saveUuidAndEmail(uuid, email);
		return "done";
	}

    @Transactional
    private void saveUuidAndEmail(String uuid, String email) {
        this.resetService.setUuidAndEmail(uuid, email);
    }
}   