package com.example.jsonprocessing.services.user;


import com.example.jsonprocessing.DTOs.user.UserWithSoldProductsDto;
import com.example.jsonprocessing.DTOs.user.UserWrapperDto;
import com.example.jsonprocessing.entities.User;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface UserService {
    User getRandomUser();
    Set<UserWithSoldProductsDto> findAllBySellingProductsBuyerIsNotNullOrderBySellingProductsBuyerFirstName() throws IOException;

    UserWrapperDto getUserWrapperDto() throws IOException;

}