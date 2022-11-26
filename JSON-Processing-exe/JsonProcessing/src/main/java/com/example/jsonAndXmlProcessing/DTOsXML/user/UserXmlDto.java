package com.example.jsonAndXmlProcessing.DTOsXML.user;

import com.example.jsonAndXmlProcessing.DTOsXML.product.ProductXmlDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserXmlDto {

    private String firstName;
    private String lastName;
    private Integer age;
    private Set<ProductXmlDto> sellingProducts;
    private Set<ProductXmlDto> boughtProducts;
    private Set<UserXmlDto> friends;


    public Set<ProductXmlDto> getSoldProducts() {
        return sellingProducts.stream().filter(p -> p.getBuyer() != null).collect(Collectors.toSet());
    }






}
