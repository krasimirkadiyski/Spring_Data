package com.example.jsonprocessing.DTOs.user;

import com.example.jsonprocessing.DTOs.product.ProductSoldDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserWithSoldProductsDto {
    private String firstName;
    private String lastName;
    private List<ProductSoldDto> sellingProducts;


}
