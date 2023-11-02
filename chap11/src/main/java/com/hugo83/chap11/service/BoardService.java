package com.hugo83.chap11.service;

import com.hugo83.chap11.dto.BoardDTO;
import com.hugo83.chap11.dto.PageRequestDTO;
import com.hugo83.chap11.dto.PageResponseDTO;

public interface BoardService {
	Long register(BoardDTO boardDTO);

	BoardDTO readOne(Long bno);

	void modify(BoardDTO boardDTO);

	void remove(Long bno);

	PageResponseDTO<BoardDTO> list(PageRequestDTO pageRequestDTO);
}
