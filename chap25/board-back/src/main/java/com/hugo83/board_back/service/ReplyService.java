package com.hugo83.board_back.service;

import java.util.Optional;

import com.hugo83.board_back.common.DataNotFoundException;
import com.hugo83.board_back.entity.Board;
import com.hugo83.board_back.entity.Reply;
import com.hugo83.board_back.entity.SiteUser;
import com.hugo83.board_back.repository.ReplyRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
// import java.util.List;

@RequiredArgsConstructor
@Service
public class ReplyService {
	
	private final ReplyRepository replyRepository;

	public void setReply(Board board, String content, SiteUser author) {
		Reply reply = Reply.builder().content(content).createDate(LocalDateTime.now())
				.board(board).build();
		reply.setAuthor(author); // 저자 추가
		this.replyRepository.save(reply);
	}

	public Reply getReply(Long bno) {
		Optional<Reply> reply = this.replyRepository.findById(bno);
		if (reply.isPresent()) {
			return reply.get();
		} else {
			throw new DataNotFoundException("reply not found");
		}
	}

	public void setReplyModify(Reply reply, String content) {
		reply.setContent(content);
		reply.setModifyDate(LocalDateTime.now());
		this.replyRepository.save(reply);
	}

	public void delReply(Reply reply) {
		this.replyRepository.delete(reply);
	}
}
