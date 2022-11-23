package com.example.jsonprocessing.DTOs.user;

import com.example.jsonprocessing.DTOs.product.ProductDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
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
}
