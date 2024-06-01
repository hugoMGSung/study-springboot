package com.hugo83.board_back.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.ResponseBody;

import com.hugo83.board_back.entity.Board;
import com.hugo83.board_back.repository.BoardRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class BoardController {

	@Autowired
	private final BoardRepository boardRepository;

	@GetMapping("/board/list")
	// @ResponseBody
	public String list(Model model) {
		List<Board> boardList = this.boardRepository.findAll();
		model.addAttribute("boardList", boardList);
		return "board/list";
	}
}
