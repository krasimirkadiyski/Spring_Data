package com.example.jsonAndXmlProcessing.DTOsJSON.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductPriceAndSellerFullNameDto {
    private String name;
    private BigDecimal price;
    private String sellerFullName;
}
