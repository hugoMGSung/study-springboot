package com.hugo83.board_back.controller;

import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.domain.Page;

import com.hugo83.board_back.entity.Board;
// import com.hugo83.board_back.entity.Reply;
// import com.hugo83.board_back.repository.BoardRepository;
import com.hugo83.board_back.service.BoardService;
// import com.hugo83.board_back.service.ReplyService;
import com.hugo83.board_back.validation.BoardForm;
import com.hugo83.board_back.validation.ReplyForm;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequestMapping("/board")
@RequiredArgsConstructor
@Controller
public class BoardController {

	private final BoardService boardService;

	@GetMapping({ "", "/list" })
	// @ResponseBody
	public String list(Model model, @RequestParam(value="page", defaultValue = "0") int page) {
		// List<Board> boardList = this.boardService.getBoardList();
		// model.addAttribute("boardList", boardList);
		Page<Board> paging = this.boardService.getList(page);
		model.addAttribute("paging", paging);
		return "board/list";
	}

	@GetMapping(value = "/detail/{Bno}")
	public String detail(Model model, @PathVariable("Bno") Long bno, ReplyForm replyForm) {
		Board board = this.boardService.getBoardDetail(bno);
		model.addAttribute("board", board);
		return "board/detail";
	}

	@GetMapping("/create")
	public String create(BoardForm boardForm) {
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
