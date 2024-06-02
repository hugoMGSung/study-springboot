package com.hugo83.board_back.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hugo83.board_back.entity.Board;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
	Board findByTitle(String title);

	Board findByTitleAndContent(String title, String content);

	List<Board> findByTitleLike(String title);

	@SuppressWarnings("null")
	Page<Board> findAll(Pageable pageable);
}
