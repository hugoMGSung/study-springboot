package com.hugo83.board_back.config;

import java.time.LocalDateTime;
import java.util.Map;

import com.hugo83.board_back.entity.SiteUser;
import com.hugo83.board_back.role.UserRole;

import lombok.Builder;
import lombok.Getter;

@Getter
public class OAuthAttributes {

	private Map<String, Object> attributes;
	private String nameAttributeKey;
	private String username;
	private String email;
	private String picture;

	@Builder
	public OAuthAttributes(Map<String, Object> attributes,
			String nameAttributeKey, String username,
			String email, String picture) {
		this.attributes = attributes;
		this.nameAttributeKey = nameAttributeKey;
		this.username = username;
		this.email = email;
		this.picture = picture;
	}

	// OAuth2User에서 반환하는 사용자 정보는 Map
	// 따라서 값 하나하나를 변환해야 한다.
	public static OAuthAttributes of(String registrationId,
			String userNameAttributeName,
			Map<String, Object> attributes) {

		return ofGoogle(userNameAttributeName, attributes);
	}

	// 구글 생성자
	private static OAuthAttributes ofGoogle(String usernameAttributeName,
			Map<String, Object> attributes) {
		return OAuthAttributes.builder()
				.username((String) attributes.get("name"))
				.email((String) attributes.get("email"))
				.picture((String) attributes.get("picture"))
				.attributes(attributes)
				.nameAttributeKey(usernameAttributeName)
				.build();
	}

	// User 엔티티 생성
	public SiteUser toEntity() {
		SiteUser tmpUser = SiteUser.builder()
				.username(username)
				.email(email)
				.picture(picture)
				.role(UserRole.USER)
				.build();

		tmpUser.setRegDate(LocalDateTime.now());
		return tmpUser;
	}
}