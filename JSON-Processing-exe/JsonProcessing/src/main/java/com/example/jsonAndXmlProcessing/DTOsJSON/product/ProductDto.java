package com.example.jsonAndXmlProcessing.DTOsJSON.product;

import com.example.jsonAndXmlProcessing.DTOsJSON.category.CategoryDto;
import com.example.jsonAndXmlProcessing.DTOsJSON.user.UserDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Set;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    private String name;
    private BigDecimal price;
    private UserDto buyer;
    private UserDto seller;
    private Set<CategoryDto> categories;
}
