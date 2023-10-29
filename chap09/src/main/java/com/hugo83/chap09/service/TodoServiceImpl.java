package com.hugo83.chap09.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.hugo83.chap09.domain.TodoVO;
import com.hugo83.chap09.dto.TodoDTO;
import com.hugo83.chap09.mapper.TodoMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {
    private final TodoMapper todoMapper; // 오토와이어링 빈 찾을 수 없음. 문제없음 --> public 으로 변경하면 오류
    private final ModelMapper modelMapper;
    @Override
    public void register(TodoDTO todoDTO) {
        log.info(modelMapper);
        TodoVO todoVO = modelMapper.map(todoDTO, TodoVO.class);
        log.info(todoVO);
        todoMapper.insert(todoVO);
    }

    @Override
    public List<TodoDTO> getAll() {
        List<TodoDTO> dtoList = todoMapper.selectAll().stream()
                .map(vo -> modelMapper.map(vo, TodoDTO.class))
                .collect(Collectors.toList());
        return dtoList;
    }

    @Override
    public TodoDTO getOne(Long tno) {
        TodoVO todoVO = todoMapper.selectOne(tno);
        TodoDTO todoDTO = modelMapper.map(todoVO, TodoDTO.class);
        return todoDTO;
    }

    @Override
    public void remove(Long tno) {
        todoMapper.delete(tno);
    }
}
