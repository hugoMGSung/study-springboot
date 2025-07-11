package com.hugo83.board_back.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hugo83.board_back.entity.Category;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
	Optional<Category> findByTitle(String title);
}
