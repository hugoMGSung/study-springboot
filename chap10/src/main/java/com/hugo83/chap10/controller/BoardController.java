package com.hugo83.chap10.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hugo83.chap10.dto.BoardDTO;
import com.hugo83.chap10.dto.PageRequestDTO;
import com.hugo83.chap10.dto.PageResponseDTO;
import com.hugo83.chap10.service.BoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/board")
@Log4j2
@RequiredArgsConstructor
public class BoardController {
	private final BoardService boardService;

	@GetMapping(value = "/list")
	public void list(PageRequestDTO pageRequestDTO, Model model) {
		PageResponseDTO<BoardDTO> responseDTO = boardService.list(pageRequestDTO);
		log.info(responseDTO);
		model.addAttribute("responseDTO", responseDTO);
	}
}
