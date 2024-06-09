package com.hugo83.board_back.entity;

import java.util.List;
import java.util.Set;
import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.CascadeType;
// import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.ManyToMany;
// import jakarta.persistence.OneToMany;
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
public class Board {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long Bno;

	@Column(name = "Title", length = 200)
	private String title;

	@Column(name="Content")
	private String content;

	@CreatedDate
	@Column(name="CreateDate", updatable = false)
	private LocalDateTime createDate;

	@LastModifiedDate
	@Column(name="ModifyDate")
	private LocalDateTime modifyDate;

	// 중요!!!
	@OneToMany(mappedBy = "board", cascade = CascadeType.REMOVE)
	private List<Reply> replyList;

	@ManyToOne
	private SiteUser author; // 사용자 중 여려명이 게시글을 적을 수 있다.

	@ManyToMany
	Set<SiteUser> voter; // 한사람이 여러 게시글을 게시글에 여러 사람이 추천할 수 있음!
}
