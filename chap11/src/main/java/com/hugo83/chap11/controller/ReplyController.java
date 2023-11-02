package com.hugo83.chap11.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hugo83.chap11.dto.ReplyDTO;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;

import lombok.extern.log4j.Log4j2;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/replies")
@Log4j2
public class ReplyController {
	// @ApiOperaion
	// @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
	// public ResponseEntity<Map<String, Long>> register(@RequestBody ReplyDTO
	// replyDTO) {
	// log.info(replyDTO);
	// Map<String, Long> resultMap = Map.of("rno", 111L);
	// return ResponseEntity.ok(resultMap);
	// }

	@PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Long> register(@Valid @RequestBody ReplyDTO replyDTO,
			BindingResult bindingResult) throws BindException {
		log.info(replyDTO);

		if (bindingResult.hasErrors()) {
			throw new BindException(bindingResult);
		}

		Map<String, Long> resultMap = new HashMap<>();
		resultMap.put("rno", 112L);

		return resultMap; // ResponseEntity.ok(resultMap);
	}
}
