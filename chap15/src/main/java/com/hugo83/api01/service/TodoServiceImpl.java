package com.hugo83.api01.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.hugo83.api01.domain.Todo;
import com.hugo83.api01.dto.PageRequestDTO;
import com.hugo83.api01.dto.PageResponseDTO;
import com.hugo83.api01.dto.TodoDTO;
import com.hugo83.api01.repository.TodoRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class TodoServiceImpl implements TodoService {
	private final TodoRepository todoRepository;
	private final ModelMapper modelMapper;

	@Override
	public Long register(TodoDTO todoDTO) {
		Todo todo = modelMapper.map(todoDTO, Todo.class);
		Long tno = todoRepository.save(todo).getTno();
		return tno;
	}

	@Override
	public TodoDTO read(Long tno) {
		Optional<Todo> result = todoRepository.findById(tno);
		Todo todo = result.orElseThrow();
		return modelMapper.map(todo, TodoDTO.class);
	}

	@Override
	public PageResponseDTO<TodoDTO> list(PageRequestDTO pageRequestDTO) {
		Page<TodoDTO> result = todoRepository.list(pageRequestDTO);

		return PageResponseDTO.<TodoDTO>withAll()
				.pageRequestDTO(pageRequestDTO)
				.dtoList(result.toList())
				.total((int) result.getTotalElements())
				.build();
	}

	@Override
	public void remove(Long tno) {
		todoRepository.deleteById(tno);
	}

	@Override
	public void modify(TodoDTO todoDTO) {

		Optional<Todo> result = todoRepository.findById(todoDTO.getTno());

		Todo todo = result.orElseThrow();

		todo.changeTitle(todoDTO.getTitle());
		todo.changeDueDate(todoDTO.getDueDate());
		todo.changeComplete(todoDTO.isComplete());

		todoRepository.save(todo);
	}
}