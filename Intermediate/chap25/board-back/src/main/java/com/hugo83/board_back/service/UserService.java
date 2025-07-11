package com.hugo83.board_back.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hugo83.board_back.common.DataNotFoundException;
import com.hugo83.board_back.entity.SiteUser;
import com.hugo83.board_back.repository.UserRepository;
import com.hugo83.board_back.role.UserRole;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RequiredArgsConstructor
@Service
@Log4j2
public class UserService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	public SiteUser create(String username, String email, String password) {
		SiteUser user = new SiteUser();
		user.setUsername(username);
		user.setEmail(email);
		user.setRegDate(LocalDateTime.now());
		user.setPassword(passwordEncoder.encode(password));
		user.setRole(UserRole.USER);
		this.userRepository.save(user);
		return user;
	}

	public SiteUser getUser(String username) {
		Optional<SiteUser> siteUser = this.userRepository.findByUsername(username);
		if (siteUser.isPresent()) {
			return siteUser.get();
		} else {
			throw new DataNotFoundException("siteuser not found");
		}
	}
	
	public SiteUser getUserByEmail(String email) {
		Optional<SiteUser> siteUser = this.userRepository.findByEmail(email);
		if (siteUser.isPresent()) {
			return siteUser.get();
		} else {
			throw new DataNotFoundException("siteuser not found");
		}
	}

	public SiteUser getUserByUsernameAndPassword(String username, String password) {
		Optional<SiteUser> _siteUser = this.userRepository.findByUsername(username);
		SiteUser siteUser;
		if (_siteUser.isPresent()) {
			siteUser = _siteUser.get();

			boolean isPasswordMathes = passwordEncoder.matches(password, siteUser.getPassword());
			log.info(String.format(">>> USERSERVICE : password matched [%s]", isPasswordMathes));

			if (isPasswordMathes)
				return siteUser;
			else
				throw new DataNotFoundException("siteuser not found");
		} else {
			throw new DataNotFoundException("siteuser not found");
		}
	}
}
