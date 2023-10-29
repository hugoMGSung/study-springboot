package com.hugo83.chap09.service;

import com.hugo83.chap09.dto.PageRequestDTO;
import com.hugo83.chap09.dto.PageResponseDTO;
import com.hugo83.chap09.dto.TodoDTO;

import java.util.List;

public interface TodoService {
    void register(TodoDTO todoDTO);
    // List<TodoDTO> getAll();
    PageResponseDTO<TodoDTO> getList(PageRequestDTO pageRequestDTO);
    TodoDTO getOne(Long tno);
    void remove(Long tno);
    void modify(TodoDTO todoDTO);
}
