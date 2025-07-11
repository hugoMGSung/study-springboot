package com.hugo83.api01.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hugo83.api01.domain.Todo;
import com.hugo83.api01.repository.search.TodoSearch;

public interface TodoRepository extends JpaRepository<Todo, Long>, TodoSearch {
}