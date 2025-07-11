package com.hugo83.chap10.dto;

import lombok.*;
import java.time.LocalDateTime;

// import javax.validation.constraints.NotEmpty;
// import javax.validation.constraints.Size;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardDTO {
	private Long bno;

	@NotEmpty
	@Size(min = 3, max = 100)
	private String title;

	@NotEmpty
	private String content;

	@NotEmpty
	private String writer;

	private LocalDateTime regDate;

	private LocalDateTime modDate;
}