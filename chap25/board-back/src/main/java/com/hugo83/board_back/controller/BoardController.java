package com.hugo83.board_back.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hugo83.board_back.entity.Board;
import com.hugo83.board_back.entity.Reply;
// import com.hugo83.board_back.repository.BoardRepository;
import com.hugo83.board_back.service.BoardService;
import com.hugo83.board_back.service.ReplyService;
import com.hugo83.board_back.validation.BoardForm;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequestMapping("/board")
@RequiredArgsConstructor
@Controller
public class BoardController {

	private final BoardService boardService;

	@GetMapping({ "", "/list" })
	// @ResponseBody
	public String list(Model model) {
		List<Board> boardList = this.boardService.getBoardList();
		model.addAttribute("boardList", boardList);
		return "board/list";
	}

	@GetMapping(value = "/detail/{Bno}")
	public String detail(Model model, @PathVariable("Bno") Long bno) {
		Board board = this.boardService.getBoardDetail(bno);
		model.addAttribute("board", board);
		return "board/detail";
	}

	@GetMapping("/create")
	public String create() {
		return "board/create";
	}

	@PostMapping("/create")
	public String create(@Valid BoardForm boardForm, BindingResult bindingResult)
	{
		if (bindingResult.hasErrors()) {
			return "board/create";
		}
		this.boardService.setBoardDetail(boardForm.getTitle(), boardForm.getContent());
		return "redirect:/board/list";
	}

	// @PostMapping("/create")
	// public String create(@RequestParam(value = "title") String title, @RequestParam(value = "content") String content) {
	// 	// 질문 저장
	// 	this.boardService.setBoardDetail(title, content);
	// 	return "redirect:/board/list";
	// }
}
