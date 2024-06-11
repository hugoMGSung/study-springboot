package com.hugo83.board_back.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hugo83.board_back.entity.Board;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
	Board findByTitle(String title);

	Board findByTitleAndContent(String title, String content);

	List<Board> findByTitleLike(String title);

	@SuppressWarnings("null")
	Page<Board> findAll(Pageable pageable);

	Page<Board> findAll(Specification<Board> spec, Pageable pageable);

	@Query("   SELECT distinct b "
            + "  FROM Board b " 
            + "  LEFT OUTER JOIN SiteUser u1 ON b.author=u1 "
            + "  LEFT OUTER JOIN Reply r ON r.board=b "
            + "  LEFT OUTER JOIN SiteUser u2 ON r.author=u2 "
            + " WHERE b.title LIKE %:kw% "
            + "    OR b.content LIKE %:kw% "
            + "    OR u1.username LIKE %:kw% "
            + "    OR r.content LIKE %:kw% "
            + "    OR u2.username LIKE %:kw% ")
	Page<Board> findAllByKeyword(@Param("kw") String kw, Pageable pageable);
}
