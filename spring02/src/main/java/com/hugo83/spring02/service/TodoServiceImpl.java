package com.hugo83.spring02.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hugo83.spring02.domain.Todo;
import com.hugo83.spring02.mapper.TodoMapper;


@Service
public class TodoServiceImpl implements TodoService {

	@Autowired
	TodoMapper todoMapper;

	@Override
	public List<Todo> selectTodos() throws Exception {
		return todoMapper.selectTodos();
	}

	@Override
	public Todo selectTodo(int tno) throws Exception {
		return todoMapper.selectTodo(tno);
	}
	
}
