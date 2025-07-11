package com.hugo83.chap11.service;

import com.hugo83.chap11.dto.PageRequestDTO;
import com.hugo83.chap11.dto.PageResponseDTO;
import com.hugo83.chap11.dto.ReplyDTO;

public interface ReplyService {
	Long register(ReplyDTO replyDTO);

	ReplyDTO read(Long rno);

	void modify(ReplyDTO replyDTO);

	void remove(Long rno);

	PageResponseDTO<ReplyDTO> getListOfBoard(Long bno, PageRequestDTO pageRequestDTO);
}