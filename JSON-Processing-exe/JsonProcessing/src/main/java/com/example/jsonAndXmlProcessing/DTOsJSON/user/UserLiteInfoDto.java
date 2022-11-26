package com.example.jsonAndXmlProcessing.DTOsJSON.user;

import lombok.Getter;

@Getter

public class UserLiteInfoDto {
    private String firstName;
    private String lastName;
    private int age;
    private UserSoldProductsDto productsSold;

    public UserLiteInfoDto(String firstName, String lastName, int age, UserSoldProductsDto productsSold) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.productsSold = productsSold;
    }
}
