package com.example.jsonAndXmlProcessing.DTOsJSON.user;

import com.example.jsonAndXmlProcessing.DTOsJSON.product.ProductLiteDto;
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
