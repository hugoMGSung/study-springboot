package com.hugo83.chap11.controller;

import org.springframework.web.bind.annotation.*;

import com.hugo83.chap11.dto.ReplyDTO;
import com.hugo83.chap11.service.ReplyService;

import jakarta.validation.Valid;

import org.springframework.http.*;
import org.springframework.validation.*;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/replies")
@Log4j2
@RequiredArgsConstructor
public class ReplyController {
	private final ReplyService replyService;

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
		// resultMap.put("rno", 112L);

		// return resultMap; // ResponseEntity.ok(resultMap);
		Long rno = replyService.register(replyDTO);
		resultMap.put("rno", rno);
		return resultMap;
	}
}
