package com.hugo83.chap10.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hugo83.chap10.domain.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {

}