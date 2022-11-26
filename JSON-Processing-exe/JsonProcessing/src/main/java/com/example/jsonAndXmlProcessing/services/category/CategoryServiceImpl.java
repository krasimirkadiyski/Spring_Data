package com.example.jsonAndXmlProcessing.services.category;

import com.example.jsonAndXmlProcessing.DTOsJSON.category.CategoryDto;
import com.example.jsonAndXmlProcessing.DTOsJSON.category.CategoryFullInformationDto;
import com.example.jsonAndXmlProcessing.entities.Category;
import com.example.jsonAndXmlProcessing.repos.CategoryRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;

import static com.example.jsonAndXmlProcessing.constants.Paths.PATH_OF_OUTPUT_CATEGORY_FULL_INFO_JSON;
import static com.example.jsonAndXmlProcessing.constants.Utils.MAPPER;
import static com.example.jsonAndXmlProcessing.constants.Utils.writeJsonIntoFile;

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
