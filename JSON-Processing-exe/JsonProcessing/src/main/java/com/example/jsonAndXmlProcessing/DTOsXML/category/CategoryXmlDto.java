package com.example.jsonAndXmlProcessing.DTOsXML.category;

import com.example.jsonAndXmlProcessing.DTOsXML.product.ProductXmlDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class CategoryXmlDto {
    private String name;
    private Set<ProductXmlDto> products;

}
