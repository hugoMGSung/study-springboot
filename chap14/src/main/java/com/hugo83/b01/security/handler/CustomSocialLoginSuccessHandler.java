package com.hugo83.b01.security.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import com.hugo83.b01.security.dto.MemberSecurityDTO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@Log4j2
@RequiredArgsConstructor
public class CustomSocialLoginSuccessHandler implements AuthenticationSuccessHandler {
	private final PasswordEncoder passwordEncoder;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

		log.info("----------------------------------------------------------");
		log.info("CustomLoginSuccessHandler onAuthenticationSuccess ..........");
		log.info(authentication.getPrincipal());

		MemberSecurityDTO memberSecurityDTO = (MemberSecurityDTO) authentication.getPrincipal();
		String encodedPw = memberSecurityDTO.getMpw();

		// 소셜로그인이고 회원의 패스워드가 1111이라면
		if (memberSecurityDTO.isSocial()
				&& (memberSecurityDTO.getMpw().equals("1111")
						|| passwordEncoder.matches("1111", memberSecurityDTO.getMpw()))) {
			log.info("Should Change Password");
			log.info("Redirect to Member Modify ");

			HttpSession session = request.getSession();
			session.setAttribute("SocialMember", memberSecurityDTO); // Session 추가
			response.sendRedirect("/member/modify"); // member/modify.html 로 이동

			return;
		} else {
			response.sendRedirect("/board/list");
		}
	}
}