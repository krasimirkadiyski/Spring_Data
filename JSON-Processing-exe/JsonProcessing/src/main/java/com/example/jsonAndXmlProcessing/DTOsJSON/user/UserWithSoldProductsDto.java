package com.example.jsonAndXmlProcessing.DTOsJSON.user;

import com.example.jsonAndXmlProcessing.DTOsJSON.product.ProductSoldDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
