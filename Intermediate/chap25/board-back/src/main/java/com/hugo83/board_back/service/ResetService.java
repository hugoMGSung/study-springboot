package com.hugo83.board_back.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.hugo83.board_back.entity.Reset;
import com.hugo83.board_back.repository.ResetRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ResetService {
	private final ResetRepository resetRepository;

	public void setUuidAndEmail(String uuid, String email) {
		Reset reset = Reset.builder().uuid(uuid).email(email).regDate(LocalDateTime.now()).build();

		this.resetRepository.save(reset);
	}
}
