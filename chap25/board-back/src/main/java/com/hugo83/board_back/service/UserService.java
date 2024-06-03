package com.hugo83.board_back.service;

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

	public SiteUser setUser(String email, String username, String password) {
		SiteUser user = new SiteUser();
		user.setEmail(email);
		user.setUsername(username);
		user.setPassword(passwordEncoder.encode(password));
		this.userRepository.save(user);
		return user;
	}
}
