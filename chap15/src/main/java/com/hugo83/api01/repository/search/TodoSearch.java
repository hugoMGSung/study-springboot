package com.hugo83.api01.repository.search;

import org.springframework.data.domain.Page;

import com.hugo83.api01.dto.PageRequestDTO;
import com.hugo83.api01.dto.TodoDTO;

public interface TodoSearch {
	Page<TodoDTO> list(PageRequestDTO pageRequestDTO);
}