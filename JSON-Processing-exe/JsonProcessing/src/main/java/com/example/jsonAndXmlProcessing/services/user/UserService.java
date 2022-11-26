package com.example.jsonAndXmlProcessing.services.user;


import com.example.jsonAndXmlProcessing.DTOsJSON.user.UserWithSoldProductsDto;
import com.example.jsonAndXmlProcessing.DTOsJSON.user.UserWrapperDto;
import com.example.jsonAndXmlProcessing.DTOsXML.user.UserSoldProductsWrapper;
import com.example.jsonAndXmlProcessing.entities.User;

import java.io.IOException;
import java.util.Set;

public interface UserService {
    User getRandomUser();
    Set<UserWithSoldProductsDto> findAllBySellingProductsBuyerIsNotNullOrderBySellingProductsBuyerFirstName() throws IOException;

    UserSoldProductsWrapper findAllBySellingProductsWithBuyer();


    UserWrapperDto getUserWrapperDto() throws IOException;

}
