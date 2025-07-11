package com.hugo83.oboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hugo83.oboard.entity.Board;

@Repository
public interface BoardRepository extends JpaRepository<Board, Integer> {
	
}
