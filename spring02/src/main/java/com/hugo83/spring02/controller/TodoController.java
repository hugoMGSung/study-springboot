package com.hugo83.spring02.controller;

import java.util.List;

// import org.springframework.stereotype.Controller;

import com.hugo83.spring02.domain.Todo;
import com.hugo83.spring02.service.TodoService;

import jakarta.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TodoController {
	
	@Resource
	TodoService todoService;

	@GetMapping("/todos")
	public List<Todo> getTodoAll() throws Exception {

		List<Todo> allTodos = todoService.selectTodos();
		return allTodos;
	}
	
	@GetMapping("/todo/{tno}")
	public @ResponseBody Todo getTodo(@PathVariable int tno) throws Exception {
		return todoService.selectTodo(tno);
	}
	
}
