package com.hugo83.board_back.common;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Header<T> {
	private LocalDateTime transactionTime;
	private String resultCode;
	private String description;
	private T data;
	// private Pagination pagination; // 일단 주석

	@SuppressWarnings("unchecked")
	public static <T> Header<T> OK() {
		return (Header<T>) Header.builder()
				.transactionTime(LocalDateTime.now())
				.resultCode("OK")
				.description("OK")
				.build();
	}

	// DATA OK
	@SuppressWarnings("unchecked")
	public static <T> Header<T> OK(T data) {
		return (Header<T>) Header.builder()
				.transactionTime(LocalDateTime.now())
				.resultCode("OK")
				.description("OK")
				.data(data)
				.build();
	}

	@SuppressWarnings("unchecked")
	public static <T> Header<T> ERROR(String description) {
		return (Header<T>) Header.builder()
				.transactionTime(LocalDateTime.now())
				.resultCode("ERROR")
				.description(description)
				.build();
	}

	// public static <T> Header<T> OK(T data, Pagination pagination) {
	// 	return (Header<T>) Header.builder()
	// 			.transactionTime(LocalDateTime.now())
	// 			.resultCode("OK")
	// 			.description("OK")
	// 			.data(data)
	// 			.pagination(pagination)
	// 			.build();
	// }
}
