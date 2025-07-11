package com.hugo83.board_back.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;

import com.hugo83.board_back.role.UserRole;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
// import jakarta.validation.constraints.NotNull;
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

	@Column
	private String picture;

	@Enumerated(EnumType.STRING) // Enum 타입은 문자열 형태로 저장해야 함
    // @NotNull // 이게 있으면 기존의 데이터 때문에 안됨.
    private UserRole role;

	@CreatedDate
	@Column(name="regDate", updatable = false)
	private LocalDateTime regDate;

	@Builder
	public SiteUser(String username, String email, String picture, UserRole role) {
		this.username = username;
		this.email = email;
		this.picture = picture;
		this.role = role;
	}

	public SiteUser update(String username, String picture) {
		this.username = username;
		this.picture = picture;

		return this;
	}

	public String getRoleKey() {
		return this.role.getValue();
	}
}