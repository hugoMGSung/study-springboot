package com.hugo83.board_back.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;
import org.springframework.stereotype.Service;
// import org.aspectj.weaver.SignatureUtils;
// import org.glassfish.jaxb.core.annotation.OverrideAnnotationOf;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import com.hugo83.board_back.common.DataNotFoundException;
import com.hugo83.board_back.entity.Board;
import com.hugo83.board_back.entity.Reply;
import com.hugo83.board_back.entity.SiteUser;
import com.hugo83.board_back.repository.BoardRepository;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BoardService {
	
	private final BoardRepository boardRepository;

	public List<Board> getBoardList() {
		return this.boardRepository.findAll();
	}

	public Page<Board> getList(int page, String kw) {
		List<Sort.Order> sorts = new ArrayList<>();
		sorts.add(Sort.Order.desc("createDate"));
		
		Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
		// 검색 추가 Specification
		// Specification<Board> spec = getBoardSearch(kw);
		//return this.boardRepository.findAll(spec, pageable);
		return this.boardRepository.findAllByKeyword(kw, pageable);
	}

	public Board getBoardDetail(Long bno) {
		Optional<Board> board = this.boardRepository.findById(bno);
		if (board.isPresent()) {
			return board.get();
		} else {
			throw new DataNotFoundException("board not found");
		}
	}

	public void setBoardDetail(String title, String content, SiteUser user) {
		Board b = Board.builder().title(title).content(content).createDate(LocalDateTime.now()).build();
		b.setAuthor(user);

		this.boardRepository.save(b);
	}

	public void setBoardModify(Board board, String title, String content) {
		board.setTitle(title);
		board.setContent(content);
		board.setModifyDate(LocalDateTime.now());

		this.boardRepository.save(board);
	}

	public void setBoardDelete(Board board) {
		this.boardRepository.delete(board);
	}

	public void setBoardVote(Board board, SiteUser siteUser) {
		board.getVoter().add(siteUser);
		this.boardRepository.save(board);
	}

	public Specification<Board> getBoardSearch(String keyword) {
		return new Specification<>() {
			private static final long serialVersionUID = 1L;
			@SuppressWarnings("null")
			@Override
			public Predicate toPredicate(Root<Board> bd, CriteriaQuery<?> query, CriteriaBuilder cb) {
				query.distinct(true); // 중복제거
				Join<Board, SiteUser> u1 = bd.join("author", JoinType.LEFT);
				Join<Board, Reply> r1 = bd.join("replyList", JoinType.LEFT);
				Join<Reply, SiteUser> u2 = r1.join("author", JoinType.LEFT);

				return cb.or(cb.like(bd.get("title"), "%" + keyword + "%"), // 제목
							cb.like(bd.get("content"), "%" + keyword + "%"), // 내용
							cb.like(u1.get("username"), "%" + keyword + "%"), // 게시글작성자
							cb.like(r1.get("content"), "%" + keyword + "%"), // 댓글내용 
							cb.like(u2.get("username"), "%" + keyword + "%")); // 댓글작성자 
			}
		};
	}
}
