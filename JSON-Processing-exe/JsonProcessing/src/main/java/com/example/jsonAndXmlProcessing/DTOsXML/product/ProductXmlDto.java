package com.example.jsonAndXmlProcessing.DTOsXML.product;

import com.example.jsonAndXmlProcessing.DTOsXML.category.CategoryXmlDto;
import com.example.jsonAndXmlProcessing.DTOsXML.user.UserXmlDto;
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
public class ProductXmlDto {
    private String name;
    private BigDecimal price;
    private UserXmlDto buyer;
    private UserXmlDto seller;
    private Set<CategoryXmlDto> categories;
    public String getBuyerFirstName(){
        return this.buyer.getFirstName();
    }
    public String getBuyerLastName(){
        return this.buyer.getLastName();
    }
}
