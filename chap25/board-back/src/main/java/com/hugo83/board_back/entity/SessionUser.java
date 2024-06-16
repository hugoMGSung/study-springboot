package com.hugo83.board_back.entity;

import java.io.Serializable;

import lombok.Getter;

@Getter
public class SessionUser implements Serializable { // 직렬화 기능
	// 인증된 사용자 정보만 필요 => name, email, picture 필드만 선언
	private String username;
	private String email;
	private String picture;

	public SessionUser(SiteUser user) {
		this.username = user.getUsername();
		this.email = user.getEmail();
		this.picture = user.getPicture();
	}
}
