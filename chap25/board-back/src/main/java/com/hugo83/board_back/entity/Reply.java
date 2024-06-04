package com.hugo83.board_back.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Reply {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long Rno;

	@Column(name = "Content")
	private String content;

	@CreatedDate
	@Column(name="CreateDate", updatable = false)
	private LocalDateTime createDate;

	// 중요!!!
	@ManyToOne
	private Board board;

	@ManyToOne
	private SiteUser author;
}
