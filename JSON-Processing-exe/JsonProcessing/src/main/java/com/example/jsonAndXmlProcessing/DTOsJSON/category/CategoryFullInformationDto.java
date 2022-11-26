package com.example.jsonAndXmlProcessing.DTOsJSON.category;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryFullInformationDto {
    private String name;
    private int productsCount;
    private double averagePrice;
    private double totalRevenue;
}
