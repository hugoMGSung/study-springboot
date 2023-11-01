package com.hugo83.chap10.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hugo83.chap10.domain.Board;
import com.hugo83.chap10.repository.search.BoardSearch; // 반드시 추가!

// , BoardSearch 반드시 추가
public interface BoardRepository extends JpaRepository<Board, Long>, BoardSearch {
	@Query(value = "select now()", nativeQuery = true)
	String getTime();
}