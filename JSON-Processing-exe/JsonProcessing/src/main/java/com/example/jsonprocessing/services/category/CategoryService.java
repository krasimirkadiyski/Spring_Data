package com.example.jsonprocessing.services.category;

import com.example.jsonprocessing.DTOs.category.CategoryFullInformationDto;
import com.example.jsonprocessing.entities.Category;

import java.io.IOException;
import java.util.Set;

public interface CategoryService {
    Category getRandomCategory();

    Set<CategoryFullInformationDto> getCategoriesFullInfo() throws IOException;

}
