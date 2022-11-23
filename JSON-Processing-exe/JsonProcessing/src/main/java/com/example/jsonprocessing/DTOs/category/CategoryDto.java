package com.example.jsonprocessing.DTOs.category;

import com.example.jsonprocessing.DTOs.product.ProductDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {
    private String name;
    private Set<ProductDto> product;
}
