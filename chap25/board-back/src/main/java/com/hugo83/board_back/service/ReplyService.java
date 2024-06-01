package com.hugo83.board_back.service;

import com.hugo83.board_back.entity.Board;
import com.hugo83.board_back.entity.Reply;
import com.hugo83.board_back.repository.ReplyRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ReplyService {
	
	private final ReplyRepository replyRepository;

	public void create(Board board, String content) {
		Reply reply = Reply.builder().content(content).createDate(LocalDateTime.now()).board(board).build();
		this.replyRepository.save(reply);
	}
}
