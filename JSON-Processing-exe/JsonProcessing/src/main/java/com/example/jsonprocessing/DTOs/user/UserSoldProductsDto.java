package com.example.jsonprocessing.DTOs.user;

import com.example.jsonprocessing.DTOs.product.ProductLiteDto;
import lombok.Getter;

import java.util.Set;
@Getter
public class UserSoldProductsDto {
    private int count;
    private Set<ProductLiteDto> soldProducts;

    public UserSoldProductsDto(Set<ProductLiteDto> soldProducts) {
        this.soldProducts = soldProducts;
        this.count = soldProducts.size();
    }
}
