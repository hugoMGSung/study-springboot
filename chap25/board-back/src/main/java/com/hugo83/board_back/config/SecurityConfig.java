package com.hugo83.board_back.config;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

// import com.hugo83.board_back.service.CustomOAuth2UserService;

import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.AuthenticationManager;
// import org.spring framework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig {

	// private final CustomOAuth2UserService customOAuth2UserService;

	// OAuth2 추가 06.16.
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests
				// .anyRequest().authenticated()
				.requestMatchers(new AntPathRequestMatcher("/**")).permitAll())
				// CORS추가
				.cors(corsConfig -> corsConfig.configurationSource(corsConfigurationSource()))
				.csrf((csrf) -> csrf
						.ignoringRequestMatchers(new AntPathRequestMatcher("/h2-console/**"))
						.disable())
				.headers((headers) -> headers
						.addHeaderWriter(new XFrameOptionsHeaderWriter(
								XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN)))
				.formLogin((formLogin) -> formLogin
						.loginPage("/user/login")
						.defaultSuccessUrl("/"))
				.logout((logout) -> logout
						.logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
						.logoutSuccessUrl("/")
						.invalidateHttpSession(true))
				// OAuth2 로그인 기능에 대한 여러 설정
				//.oauth2Login(Customizer.withDefaults()); // 아래 코드와 동일한 결과
		// .oauth2Login(
		// 		(oauth) ->
		// 			oauth.userInfoEndpoint(
		// 					(endpoint) -> endpoint.userService(customOAuth2UserService)
		// 			))
		;

		// http.csrf(csrf -> csrf.disable()); // Cross Site Request Forgery
		// http.cors(hsc -> {
		// 	hsc.configurationSource(corsConfigurationSource());
		// });
		// userInfoEndpoint() 는 deprecated and marked for removal 6.1에서 

		return http.build();
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	// ⭐️ CORS 설정
	@Bean
    CorsConfigurationSource corsConfigurationSource() {
        return request -> {
            CorsConfiguration config = new CorsConfiguration();
            config.setAllowedHeaders(Collections.singletonList("*"));
            config.setAllowedMethods(Collections.singletonList("*"));
            config.setAllowedOriginPatterns(Collections.singletonList("http://localhost:3000")); // ⭐️ 허용할 origin
            config.setAllowCredentials(true);
            return config;
        };
    }
}
