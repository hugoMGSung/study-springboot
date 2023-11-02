package com.hugo83.chap11.repository.search;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.hugo83.chap11.domain.Board;
import com.hugo83.chap11.dto.BoardListReplyCountDTO;

public interface BoardSearch {
	Page<Board> search1(Pageable pageable);

	Page<Board> searchAll(String[] types, String keyword, Pageable pageable);

	Page<BoardListReplyCountDTO> searchWithReplyCount(String[] types,
			String keyword,
			Pageable pageable);
}
