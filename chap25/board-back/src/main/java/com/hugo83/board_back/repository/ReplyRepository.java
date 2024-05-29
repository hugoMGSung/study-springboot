package com.hugo83.board_back.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hugo83.board_back.entity.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
	
}
