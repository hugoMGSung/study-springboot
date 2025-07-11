package com.hugo83.board_back.service;

import java.util.Optional;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.hugo83.board_back.common.DataNotFoundException;
import com.hugo83.board_back.entity.Category;
import com.hugo83.board_back.repository.CategoryRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CategoryService {

	private final CategoryRepository categoryRepository;

	public Category getCategoryByTitle(String title) {
        Optional<Category> category = this.categoryRepository.findByTitle(title);

        if (category.isEmpty()) {
            category = Optional.ofNullable(create(title));
        }

        if(category.isPresent()){
            return category.get();
        }else{
            throw new DataNotFoundException("question Not Found");
        }

    }

    public Category create(String title) {
        Category category = new Category();
        category.setTitle(title);
        category.setCreateDate(LocalDateTime.now());
        this.categoryRepository.save(category);
        return category;
    }
}
