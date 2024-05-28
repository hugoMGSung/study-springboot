package com.hugo83.boardback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hugo83.boardback.model.Board;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
	
}
