package com.hugo83.b01.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hugo83.b01.domain.Board;
import com.hugo83.b01.repository.search.BoardSearch;

public interface BoardRepository extends JpaRepository<Board, Long>, BoardSearch {
	@EntityGraph(attributePaths = { "imageSet" }) // Lazy에서 한번에 조인처리
	@Query("select b from Board b where b.bno =:bno")
	Optional<Board> findByIdWithImages(Long bno);
}
