package com.hugo83.board_back.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hugo83.board_back.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {
	
}
