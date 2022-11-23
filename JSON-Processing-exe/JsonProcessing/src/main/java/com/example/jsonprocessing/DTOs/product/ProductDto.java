package com.example.jsonprocessing.DTOs.product;

import com.example.jsonprocessing.DTOs.category.CategoryDto;
import com.example.jsonprocessing.DTOs.user.UserDto;
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
