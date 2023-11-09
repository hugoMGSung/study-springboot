package com.hugo83.api01.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest; // web.ignoring().requestMatchers()
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity; // deprecated
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.hugo83.api01.security.APIUserDetailsService;
import com.hugo83.api01.security.filter.APILoginFilter;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Configuration
@Log4j2
@EnableWebSecurity
// @EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class CustomSecurityConfig {
	// 신규 주입
	private final APIUserDetailsService apiUserDetailsService;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		log.info("◀◁◀◁◀◁◀◁◀◁◀◁◀◁◀◁◀◁◀ WEB CONFIGURATION         ▶▷▶▷▶▷▶▷▶▷▶▷▶▷▶▷▶▷▶");
		return (web) -> web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
	}

	@Bean
	public SecurityFilterChain filterChain(final HttpSecurity http) throws Exception {
		log.info("◀◁◀◁◀◁◀◁◀◁◀◁◀◁◀◁◀◁◀ FILTERCHAIN CONFIGURATION ▶▷▶▷▶▷▶▷▶▷▶▷▶▷▶▷▶▷▶");

		// AuthenticationManger 설정 추가
		AuthenticationManagerBuilder authenticationManagerBuilder = http
				.getSharedObject(AuthenticationManagerBuilder.class);
		authenticationManagerBuilder.userDetailsService(apiUserDetailsService).passwordEncoder(passwordEncoder());

		// Get AuthenticationManager
		AuthenticationManager authenticationManager = authenticationManagerBuilder.build();
		// 반드시 필요
		http.authenticationManager(authenticationManager);

		// APILoginFilter
		APILoginFilter apiLoginFilter = new APILoginFilter("/generateToken");
		apiLoginFilter.setAuthenticationManager(authenticationManager);
		// APILoginFilter 위치 조정
		http.addFilterBefore(apiLoginFilter, UsernamePasswordAuthenticationFilter.class);
		http.cors(cors -> cors.disable()) // Cross Origin Resource Sharing
				.csrf(csrf -> csrf.disable()); // Cross Site Request Forgery
		http.sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS)); // 세션사용 안함

		return http.build();
	}
}
