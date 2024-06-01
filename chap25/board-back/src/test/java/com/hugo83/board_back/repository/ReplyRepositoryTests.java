package com.hugo83.board_back.repository;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hugo83.board_back.entity.Board;
import com.hugo83.board_back.entity.Reply;

@SpringBootTest
public class ReplyRepositoryTests {
	@Autowired
	BoardRepository boardRepository;

	@Autowired
	ReplyRepository replyRepository;

	@Test
	void testInsertReply() {
		Optional<Board> cb = this.boardRepository.findById(2L);
		assertTrue(cb.isPresent());
		Board ob = cb.get();

		Reply rp = Reply.builder().content("아주 좋아여~~~!!!").board(ob).createDate(LocalDateTime.now()).build();
		this.replyRepository.save(rp);
		System.out.println("들어갔다고~");
	}
}
