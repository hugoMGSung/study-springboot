package com.hugo83.board_back.controller;

// import java.util.List;

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
import org.springframework.web.server.ResponseStatusException;
// import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
// import org.springframework.http.HttpStatusCode;

import com.hugo83.board_back.entity.Board;
import com.hugo83.board_back.entity.SiteUser;
// import com.hugo83.board_back.entity.Reply;
// import com.hugo83.board_back.repository.BoardRepository;
import com.hugo83.board_back.service.BoardService;
import com.hugo83.board_back.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
// import com.hugo83.board_back.service.ReplyService;
import com.hugo83.board_back.validation.BoardForm;
import com.hugo83.board_back.validation.ReplyForm;

import jakarta.servlet.http.HttpServletRequest;

import java.security.Principal;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequestMapping("/board")
@RequiredArgsConstructor
@Controller
public class BoardController {

	private final BoardService boardService;
	private final UserService userService;

	@GetMapping({ "", "/list" })
	// @ResponseBody
	public String list(Model model, @RequestParam(value = "page", defaultValue = "0") int page, 
			@RequestParam(value = "kw", defaultValue = "") String kw) {
		// List<Board> boardList = this.boardService.getBoardList();
		// model.addAttribute("boardList", boardList);
		Page<Board> paging = this.boardService.getList(page, kw);
		model.addAttribute("paging", paging);
		model.addAttribute("kw", kw);
		return "board/list";
	}

	@GetMapping(value = "/detail/{Bno}")
	public String detail(Model model, @PathVariable("Bno") Long bno, ReplyForm replyForm, HttpServletRequest request) {
		String prevUrl = request.getHeader("referer");
		Board board = this.boardService.getBoardDetail(bno);
		model.addAttribute("board", board);
		model.addAttribute("prevUrl", prevUrl);
		System.out.println(prevUrl);
		return "board/detail";
	}

	@PreAuthorize("isAuthenticated()")
	@GetMapping("/create")
	public String create(BoardForm boardForm) {
		return "board/create";
	}

	@PreAuthorize("isAuthenticated()")
	@PostMapping("/create")
	public String create(@Valid BoardForm boardForm, BindingResult bindingResult, Principal principal) {
		if (bindingResult.hasErrors()) {
			return "board/create";
		}

		SiteUser siteUser = this.userService.getUser(principal.getName());
		this.boardService.setBoardDetail(boardForm.getTitle(), boardForm.getContent(), siteUser);
		return "redirect:/board/list";
	}

	// @PostMapping("/create")
	// public String create(@RequestParam(value = "title") String title, @RequestParam(value = "content") String content) {
	// 	// 질문 저장
	// 	this.boardService.setBoardDetail(title, content);
	// 	return "redirect:/board/list";
	// }
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/modify/{Bno}")
	public String modify(BoardForm boardForm, @PathVariable("Bno") Long bno, Principal principal) {
		Board board = this.boardService.getBoardDetail(bno);
		if (!board.getAuthor().getUsername().equals(principal.getName())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
		}

		boardForm.setTitle(board.getTitle());
		boardForm.setContent(board.getContent());
		return "/board/create";
	}
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/modify/{Bno}")
	public String modify(@Valid BoardForm boardForm, BindingResult bindingResult,
			Principal principal, @PathVariable("Bno") Long bno) {
		if (bindingResult.hasErrors()) {
			return "/board/create";
		}

		Board board = this.boardService.getBoardDetail(bno);
		if (!board.getAuthor().getUsername().equals(principal.getName())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
		}

		this.boardService.setBoardModify(board, boardForm.getTitle(), boardForm.getContent());
		return String.format("redirect:/board/detail/%s", bno);
	}
	
	@PreAuthorize("isAuthenticated()")
    @GetMapping("/delete/{Bno}")
	public String delete(Principal principal, @PathVariable("Bno") Long bno) {
		Board board = this.boardService.getBoardDetail(bno);

		if (!board.getAuthor().getUsername().equals(principal.getName())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제권한이 없습니다.");
		}
		this.boardService.setBoardDelete(board);
		return "redirect:/board/list";
	}

	@PreAuthorize("isAuthenticated()")
	@GetMapping("/vote/{Bno}")
	public String boardVote(Principal principal, @PathVariable("Bno") Long bno) {
		Board board = this.boardService.getBoardDetail(bno);
		SiteUser siteUser = this.userService.getUser(principal.getName());
		this.boardService.setBoardVote(board, siteUser);

		return String.format("redirect:/board/detail/%s", bno);
	}
	
}
