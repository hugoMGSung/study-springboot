package com.hugo83.board_back.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Getter
@Setter
@Entity
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long Id;

	@Column(length = 50)
	private String title;

	private LocalDateTime createDate;
}
