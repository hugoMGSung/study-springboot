package com.hugo83.googlelogin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import com.hugo83.googlelogin.service.CustomOAuth2UserService;

// 스프링시큐리티 핵심파일!!
@Configuration      // 스프링 환경설정 파일 
@EnableWebSecurity  // 스프링시큐리티를 제어 활성화
@EnableMethodSecurity(prePostEnabled = true)   // 계정세션없는 사용자 방지
public class SecurityConfig {

    private final CustomOAuth2UserService customOAuth2UserService;    

    public SecurityConfig(CustomOAuth2UserService customOAuth2UserService) {
        this.customOAuth2UserService = customOAuth2UserService;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable())
            .formLogin(fmlg -> fmlg.disable())
            .httpBasic(htbs -> htbs.disable())
            // .oauth2Login(Customizer.withDefaults())
            .authorizeHttpRequests(auth -> auth
                                            .requestMatchers("/", "/login/**", "/oauth2/**", "/my", "/css/**", "/js/**").permitAll()
                                            .anyRequest().authenticated())
            .oauth2Login(oauth2 -> oauth2
                                        .userInfoEndpoint(uiep -> uiep.userService(customOAuth2UserService))
                                        .defaultSuccessUrl("/my", true))
        ;

        return http.build();
    }
}
