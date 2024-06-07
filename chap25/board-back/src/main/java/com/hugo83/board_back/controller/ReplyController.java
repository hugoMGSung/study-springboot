package com.hugo83.board_back.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;

import com.hugo83.board_back.entity.Board;
import com.hugo83.board_back.entity.Reply;
import com.hugo83.board_back.entity.SiteUser;
import com.hugo83.board_back.service.BoardService;
import com.hugo83.board_back.service.ReplyService;
import com.hugo83.board_back.service.UserService;
import com.hugo83.board_back.validation.ReplyForm;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import java.security.Principal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;


@RequestMapping("/reply")
@RequiredArgsConstructor
@Controller
public class ReplyController {
	private final BoardService boardService;
	private final ReplyService replyService;
	private final UserService userService;

	// @PostMapping("/create/{Bno}")
	// public String createReply(Model model, @PathVariable("Bno") Long bno, @RequestParam(value="content") String content) {
	// 	Board board = this.boardService.getBoardDetail(bno);
	// 	this.replyService.setReply(board, content);
	// 	return String.format("redirect:/board/detail/%s", bno);
	// }

	@PreAuthorize("isAuthenticated()")
	@PostMapping("/create/{Bno}")
	public String createReply(Model model, @PathVariable("Bno") Long bno,
			@Valid ReplyForm replyForm, BindingResult bindingResult, Principal principal) {
		Board board = this.boardService.getBoardDetail(bno);
		SiteUser siteUser = this.userService.getUser(principal.getName());
		if (bindingResult.hasErrors()) {
			model.addAttribute("board", board);
			return "board/detail";
		}
		this.replyService.setReply(board, replyForm.getContent(), siteUser);
		return String.format("redirect:/board/detail/%s", bno);
	}

	@PreAuthorize("isAuthenticated()")
	@GetMapping("/modify/{Rno}")
	public String modifyReply(ReplyForm replyForm, @PathVariable("Rno") Long rno, Principal principal) {
		Reply reply = this.replyService.getReply(rno);
		if (!reply.getAuthor().getUsername().equals(principal.getName())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
		}
		replyForm.setContent(reply.getContent());
		return "reply/modify";
	}
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/modify/{Rno}")
	public String modifyReply(@Valid ReplyForm replyForm, BindingResult bindingResult,
			@PathVariable("Rno") Long rno, Principal principal) {
		if (bindingResult.hasErrors()) {
			return "reply/modify";
		}
		Reply reply = this.replyService.getReply(rno);
		if (!reply.getAuthor().getUsername().equals(principal.getName())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
		}
		this.replyService.setReplyModify(reply, replyForm.getContent());
		return String.format("redirect:/board/detail/%s", reply.getBoard().getBno());
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/delete/{Rno}")
	public String deleteReply(Principal principal, @PathVariable("Rno") Long rno) {
		Reply reply = this.replyService.getReply(rno);
		if (!reply.getAuthor().getUsername().equals(principal.getName())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제권한이 없습니다.");
		}
		this.replyService.delReply(reply);
		return String.format("redirect:/board/detail/%s", reply.getBoard().getBno());
	}
}
