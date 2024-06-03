package com.hugo83.board_back.entity;

import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class SiteUser {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@Column(unique = true)
	private String email;

	@Column(unique = true)
	private String username;

	private String password;

	@CreatedDate
	@Column(name="regDate", updatable = false)
	private LocalDateTime regDate;
}
