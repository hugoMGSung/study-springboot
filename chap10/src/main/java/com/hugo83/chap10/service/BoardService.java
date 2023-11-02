package com.hugo83.chap10.service;

import com.hugo83.chap10.dto.BoardDTO;
import com.hugo83.chap10.dto.PageRequestDTO;
import com.hugo83.chap10.dto.PageResponseDTO;

public interface BoardService {
	Long register(BoardDTO boardDTO);

	BoardDTO readOne(Long bno);

	void modify(BoardDTO boardDTO);

	void remove(Long bno);

	PageResponseDTO<BoardDTO> list(PageRequestDTO pageRequestDTO);
}
