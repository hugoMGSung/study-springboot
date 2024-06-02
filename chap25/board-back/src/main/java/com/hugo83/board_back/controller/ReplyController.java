package com.hugo83.board_back.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;

import com.hugo83.board_back.entity.Board;
import com.hugo83.board_back.service.BoardService;
import com.hugo83.board_back.service.ReplyService;
import com.hugo83.board_back.validation.ReplyForm;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequestMapping("/reply")
@RequiredArgsConstructor
@Controller
public class ReplyController {
	private final BoardService boardService;
	private final ReplyService replyService;

	// @PostMapping("/create/{Bno}")
	// public String createReply(Model model, @PathVariable("Bno") Long bno, @RequestParam(value="content") String content) {
	// 	Board board = this.boardService.getBoardDetail(bno);
	// 	this.replyService.setReply(board, content);
	// 	return String.format("redirect:/board/detail/%s", bno);
	// }

	@PostMapping("/create/{Bno}")
	public String createReply(Model model, @PathVariable("Bno") Long bno,
			@Valid ReplyForm replyForm, BindingResult bindingResult) {
		Board board = this.boardService.getBoardDetail(bno);
		if (bindingResult.hasErrors()) {
			model.addAttribute("board", board);
			return "board/detail";
		}
		this.replyService.setReply(board, replyForm.getContent());
		return String.format("redirect:/board/detail/%s", bno);
	}}
