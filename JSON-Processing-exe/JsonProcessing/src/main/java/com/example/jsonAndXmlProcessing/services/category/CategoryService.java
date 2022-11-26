package com.example.jsonAndXmlProcessing.services.category;

import com.example.jsonAndXmlProcessing.DTOsJSON.category.CategoryFullInformationDto;
import com.example.jsonAndXmlProcessing.entities.Category;

import java.io.IOException;
import java.util.Set;

public interface CategoryService {
    Category getRandomCategory();

    Set<CategoryFullInformationDto> getCategoriesFullInfo() throws IOException;

}
