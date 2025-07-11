package com.hugo83.chap11.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hugo83.chap11.domain.Board;
import com.hugo83.chap11.repository.search.BoardSearch;

// , BoardSearch 반드시 추가
public interface BoardRepository extends JpaRepository<Board, Long>, BoardSearch {
	@Query(value = "select now()", nativeQuery = true)
	String getTime();
}