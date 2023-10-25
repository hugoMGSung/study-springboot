package com.hug83.chap09.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString // toString() 메서드 자동생성
@Data // Getter/Setter/ToString/RequiredArgsContructor 모두 합친것
@Builder // builder() 등 핵심소스코드 자동생성
@AllArgsConstructor // 필드에 쓴 모든 생성자만 생성
@NoArgsConstructor // 기본 생성자 생성
public class TodoDTO {
	private Long tno;
	@NotEmpty // NULL과 "" 입력 불가. " " 입력 허용
	private String title;
	@Future //
	private LocalDate dueDate;
	private boolean finished;
	@NotEmpty
	private String writer; // 신규 추가.
}