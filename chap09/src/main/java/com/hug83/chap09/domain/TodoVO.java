package com.hug83.chap09.domain;

import java.time.LocalDate;

import lombok.*;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TodoVO {
	private Long tno;
	private String title;
	private LocalDate dueDate;
	private String writer;
	private boolean finished;
}