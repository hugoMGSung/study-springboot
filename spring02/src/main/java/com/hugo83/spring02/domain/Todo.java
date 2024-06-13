package com.hugo83.spring02.domain;

import java.time.LocalDateTime;

// import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
// @Data => @Getter, @Setter, @ToString, @EqualsAndHashCode,@RequiredArgsConstructor
public class Todo {
	private int tno;

	private String title;

	private LocalDateTime dueDate;

	private String writer;

	private int isDone;
}
