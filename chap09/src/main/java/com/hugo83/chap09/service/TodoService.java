package com.hugo83.chap09.service;

import com.hugo83.chap09.dto.TodoDTO;

import java.util.List;

public interface TodoService {
    void register(TodoDTO todoDTO);
    List<TodoDTO> getAll();
    TodoDTO getOne(Long tno);
    void remove(Long tno);
}
