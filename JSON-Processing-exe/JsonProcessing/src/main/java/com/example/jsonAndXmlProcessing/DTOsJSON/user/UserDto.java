package com.example.jsonAndXmlProcessing.DTOsJSON.user;

import com.example.jsonAndXmlProcessing.DTOsJSON.product.ProductDto;
import com.example.jsonAndXmlProcessing.DTOsJSON.product.ProductLiteDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.util.stream.Collectors;

import static com.example.jsonAndXmlProcessing.constants.Utils.MAPPER;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private String firstName;
    private String lastName;
    private Integer age;
    private Set<ProductDto> sellingProducts;
    private Set<ProductDto> boughtProducts;
    private Set<UserDto> friends;

    public Set<ProductLiteDto> getSoldProductLiteDto(){
        Set<ProductLiteDto> result = this.sellingProducts
                .stream().filter(p -> p.getBuyer() != null)
                .map(p -> MAPPER.map(p,ProductLiteDto.class))
                .collect(Collectors.toSet());

        return result;
    }

    private UserSoldProductsDto toUserSoldProductsDto(){
        return new UserSoldProductsDto(getSoldProductLiteDto());
    }
    public UserLiteInfoDto toUserLiteInfoDto(){
        return new UserLiteInfoDto(firstName,lastName,age,toUserSoldProductsDto());
    }

}
