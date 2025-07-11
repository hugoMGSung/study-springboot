package com.hugo83.board_back.entity;

import java.util.Set;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
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
	
	@LastModifiedDate
	@Column(name = "ModifyDate")
	private LocalDateTime modifyDate;

	// 중요!!!
	@ManyToOne
	private Board board;

	@ManyToOne
	private SiteUser author;

	@ManyToMany
	Set<SiteUser> voter; // 한사람이 여러 게시글을 게시글에 여러 사람이 추천할 수 있음!
}
