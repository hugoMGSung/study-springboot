package com.hugo83.board_back.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor // 최초 부터 필요!
public class SiteUser {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@Column(unique = true)
	private String username;

	@Column(unique = true)
	private String email;

	private String password;

	private String sns;

	@CreatedDate
	@Column(name="regDate", updatable = false)
	private LocalDateTime regDate;

	@Builder
	public SiteUser(String username, String email, String sns) {
		this.username = username;
		this.email = email;
		this.sns = sns;
	}

	public SiteUser update(String username, String picture) {
		this.username = username;
		// this.picture = picture;

		return this;
	}
}