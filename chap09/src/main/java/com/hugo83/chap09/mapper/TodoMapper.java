package com.hugo83.chap09.mapper;

import com.hugo83.chap09.domain.TodoVO;

import java.util.List;

public interface TodoMapper {
    String getTime();
    void insert(TodoVO todoVO);
    List<TodoVO> selectAll();
    TodoVO selectOne(Long tno);
    void delete(Long tno);
}
