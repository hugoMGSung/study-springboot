package com.hugo83.chap10.repository.search;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.hugo83.chap10.domain.Board;
import com.hugo83.chap10.domain.QBoard;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;

import java.util.List;

public class BoardSearchImpl extends QuerydslRepositorySupport implements BoardSearch {
	public BoardSearchImpl() {
		super(Board.class);
	}

	@Override
	public Page<Board> search1(Pageable pageable) {
		QBoard board = QBoard.board; // Q도메인 객체
		JPQLQuery<Board> query = from(board); // select ... from board

		BooleanBuilder booleanBuilder = new BooleanBuilder(); // (
		booleanBuilder.or(board.title.contains("11")); // title like ...
		booleanBuilder.or(board.content.contains("11")); // content like ...

		// query.where(board.title.contains("1")); // where title like ... 수정
		query.where(booleanBuilder);
		query.where(board.bno.gt(0L));

		this.getQuerydsl().applyPagination(pageable, query); // paging

		List<Board> list = query.fetch();
		long count = query.fetchCount();
		return null;
	}

	@Override
	public Page<Board> searchAll(String[] types, String keyword, Pageable pageable) {
		QBoard board = QBoard.board;
		JPQLQuery<Board> query = from(board);

		if ((types != null && types.length > 0) && keyword != null) { // 검색 조건과 키워드가 있다면
			BooleanBuilder booleanBuilder = new BooleanBuilder(); // (
			for (String type : types) {
				switch (type) {
					case "t":
						booleanBuilder.or(board.title.contains(keyword));
						break;
					case "c":
						booleanBuilder.or(board.content.contains(keyword));
						break;
					case "w":
						booleanBuilder.or(board.writer.contains(keyword));
						break;
				}
			} // end for
			query.where(booleanBuilder);
		} // end if
		query.where(board.bno.gt(0L)); // bno > 0
		this.getQuerydsl().applyPagination(pageable, query); // paging
		List<Board> list = query.fetch();
		long count = query.fetchCount();
		// return null;
		return new PageImpl<>(list, pageable, count);
	}
}
