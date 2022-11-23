package com.example.jsonprocessing.services.category;

import com.example.jsonprocessing.entities.Category;
import com.example.jsonprocessing.repos.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService{
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category getRandomCategory() {
       return categoryRepository.getRandomCategory();
    }
}
