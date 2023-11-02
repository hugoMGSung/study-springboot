package com.hugo83.b01.service;

import com.hugo83.b01.dto.BoardDTO;
import com.hugo83.b01.dto.BoardListReplyCountDTO;
import com.hugo83.b01.dto.PageRequestDTO;
import com.hugo83.b01.dto.PageResponseDTO;

public interface BoardService {

    Long register(BoardDTO boardDTO);

    BoardDTO readOne(Long bno);

    void modify(BoardDTO boardDTO);

    void remove(Long bno);

    PageResponseDTO<BoardDTO> list(PageRequestDTO pageRequestDTO);

    // 댓글의 숫자까지 처리
    PageResponseDTO<BoardListReplyCountDTO> listWithReplyCount(PageRequestDTO pageRequestDTO);
}
