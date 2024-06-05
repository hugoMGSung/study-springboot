package com.hugo83.board_back.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.hugo83.board_back.common.DataNotFoundException;
import com.hugo83.board_back.entity.Board;
import com.hugo83.board_back.entity.SiteUser;
import com.hugo83.board_back.repository.BoardRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BoardService {
	
	private final BoardRepository boardRepository;

	public List<Board> getBoardList() {
		return this.boardRepository.findAll();
	}

	public Page<Board> getList(int page) {
		List<Sort.Order> sorts = new ArrayList<>();
		sorts.add(Sort.Order.desc("createDate"));
		
		Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
		return this.boardRepository.findAll(pageable);
	}

	public Board getBoardDetail(Long bno) {
		Optional<Board> board = this.boardRepository.findById(bno);
		if (board.isPresent()) {
			return board.get();
		} else {
			throw new DataNotFoundException("board not found");
		}
	}

	public void setBoardDetail(String title, String content, SiteUser user) {
		Board b = Board.builder().title(title).content(content).createDate(LocalDateTime.now()).build();
		b.setAuthor(user);

		this.boardRepository.save(b);
	}

	public void setBoardModify(Board board, String title, String content) {
		board.setTitle(title);
		board.setContent(content);
		board.setModifyDate(LocalDateTime.now());

		this.boardRepository.save(board);
	}

	public void setBoardDelete(Board board) {
		this.boardRepository.delete(board);
	}
}
