package com.example.jsonprocessing.services.category;

import com.example.jsonprocessing.DTOs.category.CategoryDto;
import com.example.jsonprocessing.DTOs.category.CategoryFullInformationDto;
import com.example.jsonprocessing.entities.Category;
import com.example.jsonprocessing.repos.CategoryRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;

import static com.example.jsonprocessing.constants.Paths.PATH_OF_OUTPUT_CATEGORY_FULL_INFO_JSON;
import static com.example.jsonprocessing.constants.Utils.MAPPER;
import static com.example.jsonprocessing.constants.Utils.writeJsonIntoFile;

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

    @Override
    public Set<CategoryFullInformationDto> getCategoriesFullInfo() throws IOException {
       Set<Category> all = categoryRepository.getAll();
        Set<CategoryDto> catDto = all.stream().map(c -> MAPPER.map(c, CategoryDto.class)).collect(Collectors.toSet());
        Set<CategoryFullInformationDto> result = catDto.stream().map(c -> MAPPER.map(c, CategoryFullInformationDto.class)).collect(Collectors.toSet());
        writeJsonIntoFile(result,PATH_OF_OUTPUT_CATEGORY_FULL_INFO_JSON);
        return result;
    }


}
