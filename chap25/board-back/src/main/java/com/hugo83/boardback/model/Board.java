package com.hugo83.boardback.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "board")
@Builder
public class Board {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "bno")
	private Long bno;

	@Column(name = "title")
	private String title;

	@Column(name = "content")
	private String content;

	@Column(name = "writer")
	private String writer;

	@CreatedDate
	@Column(name = "regdate", updatable = false)
	private LocalDateTime regDate;

	@LastModifiedDate
	@Column(name = "moddate")
	private LocalDateTime modDate;
}
