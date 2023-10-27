package com.hugo83.chap09.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.hugo83.chap09.domain.TodoVO;
import com.hugo83.chap09.dto.TodoDTO;
import com.hugo83.chap09.mapper.TodoMapper;

@Service
@Log4j2
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {
    private final TodoMapper todoMapper; // 오토와이어링 빈 찾을 수 없음. 문제없음.
    private final ModelMapper modelMapper;
    @Override
    public void register(TodoDTO todoDTO) {
        log.info(modelMapper);
        TodoVO todoVO = modelMapper.map(todoDTO, TodoVO.class);
        log.info(todoVO);
        todoMapper.insert(todoVO);
    }
}
