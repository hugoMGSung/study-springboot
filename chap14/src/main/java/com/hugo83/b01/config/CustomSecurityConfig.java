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
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.hugo83.b01.security.CustomUserDetailsService;
import com.hugo83.b01.security.handler.Custom403Handler;
import com.hugo83.b01.security.handler.CustomSocialLoginSuccessHandler;

import java.util.Arrays;

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
	public AuthenticationSuccessHandler authenticationSuccessHandler() {
		return new CustomSocialLoginSuccessHandler(passwordEncoder());
	}

	@Bean
	public PersistentTokenRepository persistentTokenRepository() {
		JdbcTokenRepositoryImpl repo = new JdbcTokenRepositoryImpl();
		repo.setDataSource(dataSource);
		return repo;
	}

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOriginPatterns(Arrays.asList("*"));
		configuration.setAllowedMethods(Arrays.asList("HEAD", "GET", "POST", "PUT", "DELETE"));
		configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));
		configuration.setAllowCredentials(true);
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		log.info("------------configure-------------------");

		// CSRF 토큰 비활성화
		// http.csrf().disable();
		// http.cors(cors -> cors.disable());
		// CORS 변경
		http.cors(hsc -> {
			hsc.configurationSource(corsConfigurationSource());
		});
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

		http.logout(logout -> logout.logoutSuccessUrl("/board/list"));

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
		http.oauth2Login(ol -> ol.loginPage("/member/login").successHandler(authenticationSuccessHandler()));
		// oauth2Login().loginPage() deprecated

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
