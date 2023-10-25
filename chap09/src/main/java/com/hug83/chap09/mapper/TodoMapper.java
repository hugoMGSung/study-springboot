package com.hug83.chap09.mapper;

import com.hug83.chap09.domain.TodoVO;

public interface TodoMapper {
	String getTime();

	void insert(TodoVO todoVO);

	// List<TodoVO> selectAll();

	// TodoVO selectOne(Long tno);

	// void delete(Long tno);

	// void update(TodoVO todoVO);
	// List<TodoVO> selectList(pageRequestDTO pageRequestDTO);
	// int getCount(PageRequestDTO pageRequestDTO);
}