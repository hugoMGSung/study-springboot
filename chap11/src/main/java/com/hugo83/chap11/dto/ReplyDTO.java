package com.hugo83.chap11.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReplyDTO {
	private Long rno;

	@NotNull
	private Long bno;

	// @NotNull
	// private Long board_bno; // ???

	@NotEmpty
	private String replyText;

	@NotEmpty
	private String replyer;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime regDate;

	@JsonIgnore
	private LocalDateTime modDate;
}