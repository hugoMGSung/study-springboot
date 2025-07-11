package com.hugo83.b01.security;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.hugo83.b01.domain.Member;
import com.hugo83.b01.domain.MemberRole;
import com.hugo83.b01.repository.MemberRepository;
import com.hugo83.b01.security.dto.MemberSecurityDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
	private final MemberRepository memberRepository;
	private final PasswordEncoder passwordEncoder;

	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		log.info("userRequest >>>>>>>>>>>>> ");
		log.info(userRequest);

		log.info("OAuth2 USER <<<<<<<<<<<<<<<<<<<<<<");

		ClientRegistration clientRegistration = userRequest.getClientRegistration();
		String clientName = clientRegistration.getClientName();

		log.info("NAME: " + clientName);

		OAuth2User oAuth2User = super.loadUser(userRequest);
		Map<String, Object> paramMap = oAuth2User.getAttributes();

		// paramMap.forEach((k, v) -> {
		// log.info("==============================================");
		// log.info(k + " :: " + v);
		// });

		String email = null;
		String uid = null;
		switch (clientName) {
			case "Google":
				email = paramMap.get("email").toString();
				uid = paramMap.get("given_name").toString() + paramMap.get("family_name").toString();
				log.info("GOOGLE EMAIL >>>>>>> " + email);
				log.info("GOOGLE UserName >>>>>>> " + uid);

				break;

			default:
				break;
		}

		return generateDTO(email, paramMap);
	}

	// Member 테이블에 같은 사용자 이메일이 없을때 처리
	private OAuth2User generateDTO(String email, Map<String, Object> params) {
		Optional<Member> result = memberRepository.findByEmail(email);

		// DB에 같은 이메일이 없다면
		if (result.isEmpty()) {
			// 회원 추가 -- mid는 이메일 주소/ 패스워드는 1111
			Member member = Member.builder()
					.mid(email)
					.mpw(passwordEncoder.encode("1111"))
					.email(email)
					.social(true)
					.build();
			member.addRole(MemberRole.USER);
			memberRepository.save(member);

			// MemberSecurityDTO 구성 및 반환
			MemberSecurityDTO memberSecurityDTO = new MemberSecurityDTO(email, "1111", email, false, true,
					Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")));
			memberSecurityDTO.setProps(params);

			return memberSecurityDTO;
		} else {
			Member member = result.get();
			MemberSecurityDTO memberSecurityDTO = new MemberSecurityDTO(
					member.getMid(),
					member.getMpw(),
					member.getEmail(),
					member.isDel(),
					member.isSocial(),
					member.getRoleSet()
							.stream().map(memberRole -> new SimpleGrantedAuthority("ROLE_" + memberRole.name()))
							.collect(Collectors.toList()));

			return memberSecurityDTO;
		}
	}
}
