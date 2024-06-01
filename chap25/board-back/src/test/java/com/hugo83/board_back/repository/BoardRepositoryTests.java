package com.hugo83.board_back.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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

	@Test
	void testSelectBoard() {
		List<Board> all = this.boardRepository.findAll();
		assertEquals(4, all.size());

		Board b = all.get(0);
		assertEquals("이게 머여.", b.getTitle());
	}

	@Test
	void testUpdateBoard() {
		Optional<Board> b = this.boardRepository.findById(1L);
		assertTrue(b.isPresent());
		Board ub = b.get();
		ub.setTitle("이건 바뀐거여!");
		this.boardRepository.save(ub);
		System.out.println("수정됨!");
	}
}
