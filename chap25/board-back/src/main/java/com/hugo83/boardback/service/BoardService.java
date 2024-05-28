package com.hugo83.boardback.service;

import com.hugo83.boardback.dto.BoardDTO;
import com.hugo83.boardback.dto.PageRequestDTO;
import com.hugo83.boardback.dto.PageResponseDTO;

public interface BoardService {
	Long register(BoardDTO boardDTO);

	BoardDTO readOne(Long bno);

	void modify(BoardDTO boardDTO);

	void remove(Long bno);

	PageResponseDTO<BoardDTO> list(PageRequestDTO pageRequestDTO);
}
