package com.hugo83.board_back.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BoardServiceTests {
	@Autowired
	private BoardService boardService;

	@Test
	void testHugoBoardService() {
		for (int i = 0; i <= 300; i++) {
			String title = String.format("대량 테스트입니다:[%03d]", i);
			String content = "내용은 뭐...";
			this.boardService.setBoardDetail(title, content, null);
		}
	}
}
