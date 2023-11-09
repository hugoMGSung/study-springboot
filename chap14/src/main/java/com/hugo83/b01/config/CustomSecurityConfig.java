package com.hugo83.b01.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import com.hugo83.b01.security.CustomUserDetailsService;
import com.hugo83.b01.security.handler.Custom403Handler;

@Log4j2
@Configuration
@RequiredArgsConstructor
// @EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class CustomSecurityConfig {
	private final DataSource dataSource;
	private final CustomUserDetailsService userDetailsService;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public PersistentTokenRepository persistentTokenRepository() {
		JdbcTokenRepositoryImpl repo = new JdbcTokenRepositoryImpl();
		repo.setDataSource(dataSource);
		return repo;
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		log.info("------------configure-------------------");

		// CSRF 토큰 비활성화
		// http.csrf().disable();
		http.cors(cors -> cors.disable());
		http.csrf(csrf -> csrf.disable()); // http.csrf() is deprecated.

		http.authorizeHttpRequests(authz -> authz
				.requestMatchers("/member/login", "/member/join").permitAll()
				.anyRequest().authenticated());
		// 커스텀 로그인 페이지
		// http.formLogin().loginPage("/member/login");
		// http.formLogin((formLogin) -> formLogin
		// .loginPage("/member/login")
		// .usernameParameter("username")
		// .passwordParameter("password")
		// // .loginProcessingUrl("/login/login-proc")
		// .defaultSuccessUrl("/", true));
		http.formLogin(login -> login
				.loginPage("/member/login")
				.defaultSuccessUrl("/board/list", false));

		// http.rememberMe() // rememberMe() 역시 deprecated.
		// .key("12345678") // key 변경도 필요
		// .tokenRepository(persistentTokenRepository())
		// .userDetailsService(userDetailsService)
		// .tokenValiditySeconds(60 * 60 * 24 * 30);
		http.rememberMe(rm -> rm.key("12345678")
				.tokenRepository(persistentTokenRepository())
				.userDetailsService(userDetailsService)
				.tokenValiditySeconds(60 * 60 * 24 * 30));

		http.exceptionHandling(x -> x.accessDeniedHandler(accessDeniedHandler())); // 403 exceptionHandling() deprecated
		http.oauth2Login(ol -> ol.loginPage("/member/login")); // oauth2Login().loginPage() deprecated

		return http.build();
	}

	@Bean
	public AccessDeniedHandler accessDeniedHandler() {
		return new Custom403Handler();
	}

	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		log.info("------------web configure-------------------");
		return (web) -> web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
	}
}
