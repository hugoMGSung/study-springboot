package com.hugo83.chap11.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Board extends BaseEntity {
	// GenerationType.IDENTITY -> MySQL, MariaDB, SQL Server, DB2, PostgreSQL...
	// GenerationType.SEQUENCE -> Oracle, H2, DB2, PostgreSQL (@SequenceGenerator
	// 필요)
	// GenerationType.AUTO -> 기본
	// GenerationType.TABLE -> 키생성용 테이블
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long bno;

	@Column(length = 500, nullable = false) // 컬럼의 길이와 null허용여부
	private String title;

	@Column(length = 2000, nullable = false)
	private String content;

	@Column(length = 50, nullable = false)
	private String writer;

	public void change(String title, String content) {
		this.title = title;
		this.content = content;
	}
}
