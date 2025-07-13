package com.hugo83.googlelogin.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

public class CustomOAuth2User implements OAuth2User, UserDetails  {

    private final UserDto userDto;

    // public CustomOAuth2User(OAuth2Response oAuth2Response, String role) {
        
    //     this.oAuth2Response = oAuth2Response;
    //     this.role = role;
    // }

    public CustomOAuth2User(UserDto userDto) {
        this.userDto = userDto;
    }

    @Override
    public Map<String, Object> getAttributes() {
        
        return null; // 구글과 네이버의 어트리뷰트가 너무 다르기때문에 사용안함
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        
        Collection<GrantedAuthority> collection = new ArrayList<>();

        collection.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return userDto.getRole();
            }
        });

        return collection;
    }

    @Override
    public String getName() {
        return userDto.getName();
    }

    public String getUsername() {
        return userDto.getUsername();
    }

    @Override
    public String getPassword() {
        // TODO Auto-generated method stub
        return "";
    }

}
