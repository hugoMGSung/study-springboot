package com.hugo83.boardback.repository.search;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.hugo83.boardback.model.Board;

public interface BoardSearch {
	Page<Board> search1(Pageable pageable);

	Page<Board> searchAll(String[] types, String keyword, Pageable pageable);
}
