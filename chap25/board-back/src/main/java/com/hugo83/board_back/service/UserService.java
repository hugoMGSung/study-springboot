package com.hugo83.board_back.service;

import java.time.LocalDateTime;

// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hugo83.board_back.entity.SiteUser;
import com.hugo83.board_back.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	public SiteUser create(String username, String email, String password) {
		SiteUser user = new SiteUser();
		user.setUsername(username);
		user.setEmail(email);
		user.setRegDate(LocalDateTime.now());
		user.setPassword(passwordEncoder.encode(password));
		this.userRepository.save(user);
		return user;
	}
}
