package com.hugo83.api01.service;

import com.hugo83.api01.dto.PageRequestDTO;
import com.hugo83.api01.dto.PageResponseDTO;
import com.hugo83.api01.dto.TodoDTO;

import jakarta.transaction.Transactional;

@Transactional
public interface TodoService {
	Long register(TodoDTO todoDTO);

	TodoDTO read(Long tno);

	PageResponseDTO<TodoDTO> list(PageRequestDTO pageRequestDTO);

	void remove(Long tno);

	void modify(TodoDTO todoDTO);
}