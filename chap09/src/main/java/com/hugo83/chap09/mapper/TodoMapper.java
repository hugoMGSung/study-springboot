package com.hugo83.chap09.mapper;

import com.hugo83.chap09.domain.TodoVO;

public interface TodoMapper {
    String getTime();
    void insert(TodoVO todoVO);
}
