package com.hug83.chap09.service;

import com.hug83.chap09.dto.PageRequestDTO;
import com.hug83.chap09.dto.PageResponseDTO;
import com.hug83.chap09.dto.TodoDTO;

public interface TodoService {

	void register(TodoDTO todoDTO);

	// List<TodoDTO> getAll();

	// PageResponseDTO<TodoDTO> getList(PageRequestDTO pageRequestDTO);

	// TodoDTO getOne(Long tno);

	// void remove(Long tno);

	// void modify(TodoDTO todoDTO);
}
