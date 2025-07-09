package com.hugo83.googlelogin.service;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import com.hugo83.googlelogin.dto.CustomOAuth2User;
import com.hugo83.googlelogin.dto.GoogleResponse;
import com.hugo83.googlelogin.dto.NaverResponse;
import com.hugo83.googlelogin.dto.OAuth2Response;
import com.hugo83.googlelogin.dto.UserDto;
import com.hugo83.googlelogin.entity.MemberEntity;
import com.hugo83.googlelogin.repository.MemberRepository;

// import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final MemberRepository memberRepository;

    // private AuthenticationManager authenticationManager;  // 자동로그인용 계정관리자.

    @SuppressWarnings("later")
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        
        OAuth2User oAuth2User = super.loadUser(userRequest);
        System.out.println(oAuth2User.getAttributes());

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        
        OAuth2Response oAuth2Response = null;

        if (registrationId.equals("naver")) {
            oAuth2Response = new NaverResponse(oAuth2User.getAttributes());
        } else if (registrationId.equals("google")) {
            oAuth2Response = new GoogleResponse(oAuth2User.getAttributes());
        } else {
            return null;
        }

        // 리소스 서버에서 발급받은 값으로 특정사용자 이름을 만들어줌
        String username = oAuth2Response.getProvider() + "_" + oAuth2Response.getProviderId();
        
        MemberEntity existMember = memberRepository.findByUsername(username);

        if (existMember == null) {  // 한번도 들어오지 않은 사람
            MemberEntity memberEntity = new MemberEntity();
            memberEntity.setUsername(username);
            memberEntity.setEmail(oAuth2Response.getEmail());
            memberEntity.setName(oAuth2Response.getName());
            memberEntity.setRole("ROLE_USER");
            
            memberRepository.save(memberEntity);

            UserDto userDto = new UserDto();
            userDto.setUsername(username);
            userDto.setName(oAuth2Response.getName());
            userDto.setRole("ROLE_USER");

            return new CustomOAuth2User(userDto);
        } else {  // 들어온적이 있는 사람

            existMember.setUsername(username);
            existMember.setEmail(oAuth2Response.getEmail());
            existMember.setName(oAuth2Response.getName());
            existMember.setRole("ROLE_USER");

            memberRepository.save(existMember);

            UserDto userDto = new UserDto();
            userDto.setUsername(existMember.getUsername());
            userDto.setName(existMember.getName());
            userDto.setRole("ROLE_USER");

            return new CustomOAuth2User(userDto);
        }  
    }
}
