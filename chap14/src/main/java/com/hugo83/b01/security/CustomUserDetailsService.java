package com.hugo83.b01.security;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hugo83.b01.domain.Member;
import com.hugo83.b01.repository.MemberRepository;
import com.hugo83.b01.security.dto.MemberSecurityDTO;

import groovyjarjarantlr4.v4.parse.ANTLRParser.parserRule_return;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
	private final MemberRepository memberRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("loadUserByUsername : " + username);
		Optional<Member> result = memberRepository.getWithRoles(username);

		// UserDetails userDetails = User.builder().username("user1")
		// .password(passwordEncoder.encode("1111")) // 패스워드 인코딩 필요
		// .authorities("ROLE USER")
		// .build();

		if (result.isEmpty()) { // 해당 아이디를 가진 사용자가 없다면
			throw new UsernameNotFoundException("username not found...");
		}

		Member member = result.get();

		MemberSecurityDTO memberSecurityDTO = new MemberSecurityDTO(member.getMid(), member.getMpw(),
				member.getEmail(), member.isDel(), false,
				member.getRoleSet()
						.stream().map(memberRole -> new SimpleGrantedAuthority("ROLE_" + memberRole.name()))
						.collect(Collectors.toList()));

		log.info("memberSecurityDTO");
		log.info(memberSecurityDTO);

		return memberSecurityDTO;
	}

}
