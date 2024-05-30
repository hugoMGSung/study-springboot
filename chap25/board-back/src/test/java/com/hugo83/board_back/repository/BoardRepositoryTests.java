package com.hugo83.board_back.repository;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hugo83.board_back.entity.Board;

@SpringBootTest
public class BoardRepositoryTests {
	@Autowired
	private BoardRepository boardRepository;

	@Test
	void testInsertBoard() {
		Board b1 = new Board();
		b1.setTitle("이게 머여.");
		b1.setContent("내용이 머여.");
		b1.setCreateDate(LocalDateTime.now());
		this.boardRepository.save(b1);

		Board b2 = Board.builder().title("이건 또 뭐여.").content("이럼 안돼!")
				.createDate(LocalDateTime.now()).build();
		this.boardRepository.save(b2);
	}
}
