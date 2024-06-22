package com.hugo83.board_back.restcontroller;

import org.springframework.web.bind.annotation.RestController;

import com.hugo83.board_back.common.Header;
import com.hugo83.board_back.entity.Board;
import com.hugo83.board_back.entity.Category;
import com.hugo83.board_back.service.BoardService;
import com.hugo83.board_back.service.CategoryService;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class RestBoardController {
	
	private final BoardService boardService;
	private final CategoryService categoryService; // 카테고리 추가

	@GetMapping("/board/{category}")
	@ResponseBody
	public Header<Page<Board>> list(@RequestParam(value = "page", defaultValue = "0") int page,
						@PathVariable("category") String category,
			@RequestParam(value = "kw", defaultValue = "") String kw) {
		// List<Board> boardList = this.boardService.getBoardList();
		// model.addAttribute("boardList", boardList);
		Category category1 = this.categoryService.getCategoryByTitle(category);
		Page<Board> paging = this.boardService.getList(page, kw, category1);
		Header<Page<Board>> result = Header.OK(paging);
		// model.addAttribute("paging", paging);
		// model.addAttribute("kw", kw);
		// model.addAttribute("category", category);

		return result;
	}

	@GetMapping("/board/{bno}")
	@ResponseBody
	public Header<Board> detail(@PathVariable("bno") Long bno) {
		Board board = this.boardService.hitBoard(bno);
		Header<Board> result = Header.OK(board);

		return result;
	}
	
	@PostMapping("/board")
	public Header<Board> save(@RequestBody Board board) {
		Board _board = this.boardService.setBoardDetailReact(board.getTitle(), board.getContent(), null, "freeboard");

		return Header.OK(_board);
	}
	
	@PatchMapping("/board/{bno}")
	public Header<Board> update(@RequestBody Board board) {
		Board _board = this.boardService.setBoardModifyReact(board, board.getTitle(), board.getContent());

		return Header.OK(_board);
	}

	@DeleteMapping("/board/{bno}") 
	public Header<String> delete(@PathVariable("bno") Long bno) {
		try	{
			Board board = this.boardService.getBoardDetail(bno);

			if (board != null) {
				this.boardService.setBoardDelete(board);
				return Header.OK();
			} else {
				return Header.ERROR("ERROR");
			}
		} catch (Exception e) {
			return Header.ERROR("ERROR");
		}
	}

}
