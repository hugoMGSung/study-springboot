package com.hugo83.boardback.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.swing.text.html.Option;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.hugo83.boardback.dto.BoardDTO;
import com.hugo83.boardback.dto.PageRequestDTO;
import com.hugo83.boardback.dto.PageResponseDTO;
import com.hugo83.boardback.model.Board;
import com.hugo83.boardback.repository.BoardRepository;

import jakarta.annotation.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class BoardServiceImpl implements BoardService {

	private final ModelMapper modelMapper;

	private final BoardRepository boardRepository;

	@Override
	public Long register(BoardDTO boardDTO) {
		Board board = modelMapper.map(boardDTO, Board.class);
		Long bno = boardRepository.save(board).getBno();
		log.info("Board inserted!");
		return bno;
	}

	@Override
	public BoardDTO readOne(Long bno) {
		Optional<Board> result = boardRepository.findById(bno);
		Board board = result.orElseThrow();
		BoardDTO boardDTO = modelMapper.map(board, BoardDTO.class);
		
		return boardDTO;
	}

	@Override
	public void modify(BoardDTO boardDTO) {
		Optional<Board> result = boardRepository.findById(boardDTO.getBno());

		Board board = result.orElseThrow();
		board.setTitle(boardDTO.getTitle());
		board.setContent(boardDTO.getContent());
		board.setModDate(LocalDateTime.now());

		boardRepository.save(board);
	}

	@Override
	public void remove(Long bno) {
		boardRepository.deleteById(bno);
	}

	@Override
	public PageResponseDTO<BoardDTO> list(PageRequestDTO pageRequestDTO) {
		String[] types = pageRequestDTO.getTypes();
        String keyword = pageRequestDTO.getKeyword();
        Pageable pageable = pageRequestDTO.getPageable("bno");

        // Page<Board> result = boardRepository.searchAll(types, keyword, pageable);

        // List<BoardDTO> dtoList = result.getContent().stream()
        //         .map(board -> modelMapper.map(board,BoardDTO.class)).collect(Collectors.toList());


        // return PageResponseDTO.<BoardDTO>withAll()
        //         .pageRequestDTO(pageRequestDTO)
        //         .dtoList(dtoList)
        //         .total((int)result.getTotalElements())
		//         .build();
		return null;
	}	
}
