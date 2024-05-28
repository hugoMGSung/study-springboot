package com.hugo83.boardback.service;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hugo83.boardback.dto.BoardDTO;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class BoardServiceTests {
	@Autowired
	private BoardService boardService;

	@Test
	public void testRegister() {
		log.info(boardService.getClass().getName());

		BoardDTO boardDTO = BoardDTO.builder()
				.title("샘플 타이틀!")
				.content("게시글 내용입니다요.")
				.writer("글쓴이")
				.regDate(LocalDateTime.now())
				.build();

		Long bno = boardService.register(boardDTO);
		log.info("bno" + bno);
	}
	
	@Test
	public void testModify() {
		BoardDTO boardDTO = BoardDTO.builder()
					.bno(50L)
					.title("내용 수정... 50")
					.content("수정... 50")
				.build();
		boardService.modify(boardDTO);
	}
}
