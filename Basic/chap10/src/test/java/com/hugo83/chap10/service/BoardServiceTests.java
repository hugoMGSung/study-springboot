package com.hugo83.chap10.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hugo83.chap10.dto.BoardDTO;
import com.hugo83.chap10.dto.PageRequestDTO;
import com.hugo83.chap10.dto.PageResponseDTO;

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
				.title("한글 타이틀...")
				.content("샘플 컨텐트 내용...")
				.writer("유저76")
				.build();

		Long bno = boardService.register(boardDTO);
		log.info("bno: " + bno);
	}

	@Test
	public void testModify() {
		// 변경에 필요한 데이터만
		BoardDTO boardDTO = BoardDTO.builder()
				.bno(101L)
				.title("Updated....101")
				.content("Updated content 101...")
				.build();

		boardService.modify(boardDTO);
	}

	@Test
	public void testRemove() {
		boardService.remove(2L);
	}

	@Test
	public void testList() {
		PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
				.type("tcw")
				.keyword("1")
				.page(1)
				.size(10)
				.build();
		PageResponseDTO<BoardDTO> responseDTO = boardService.list(pageRequestDTO);
		log.info(responseDTO);
	}
}
