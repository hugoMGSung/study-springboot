package com.hugo83.chap11.controller.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.log4j.Log4j2;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Log4j2
public class CustomRestAdvice {
	@ExceptionHandler(BindException.class)
	@ResponseStatus(HttpStatus.EXPECTATION_FAILED)
	public ResponseEntity<Map<String, String>> handleBindException(BindException e) {
		log.error(e);
		Map<String, String> errorMap = new HashMap<>();

		if (e.hasErrors()) {
			BindingResult bidingResult = e.getBindingResult();
			bidingResult.getFieldErrors().forEach(fieldError -> {
				errorMap.put(fieldError.getField(), fieldError.getCode());
			});
		}

		return ResponseEntity.badRequest().body(errorMap);
	}
}
