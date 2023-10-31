package com.hugo83.chap12.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hugo83.chap12.domain.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {

}