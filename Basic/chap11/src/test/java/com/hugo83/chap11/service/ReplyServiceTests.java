package com.hugo83.chap11.service;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hugo83.chap11.dto.BoardDTO;
import com.hugo83.chap11.dto.ReplyDTO;

@SpringBootTest
@Log4j2
public class ReplyServiceTests {
	@Autowired
	private ReplyService replyService;

	@Test
	public void testRegister() {
		ReplyDTO replyDTO = ReplyDTO.builder()
				.bno(10L)
				// .board_bno(10L)
				.replyText("ReplyDTO 텍스트")
				.replyer("리플라이어")
				.build();

		log.info(replyService.register(replyDTO));
	}
}
