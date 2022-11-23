package com.example.jsonprocessing.services.user;


import com.example.jsonprocessing.DTOs.user.UserWithSoldProductsDto;
import com.example.jsonprocessing.entities.User;
import com.example.jsonprocessing.repos.UserRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;

import static com.example.jsonprocessing.constants.Paths.PATH_OF_OUTPUT_USER_SOLD_PRODUCTS_JSON;
import static com.example.jsonprocessing.constants.Utils.MAPPER;
import static com.example.jsonprocessing.constants.Utils.writeJsonIntoFile;

@Service
public class UserServiceImpl implements UserService{
   private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getRandomUser() {
        return userRepository.getRandomUser();
    }

    @Override
    public Set<UserWithSoldProductsDto> findAllBySellingProductsBuyerIsNotNullOrderBySellingProductsBuyerFirstName() throws IOException {
        Set<UserWithSoldProductsDto> users = userRepository
                .findAllByOrderByLastNameAscFirstNameAsc()
                .stream().map(user -> MAPPER.map(user, UserWithSoldProductsDto.class))
                .collect(Collectors.toSet());
        users.forEach(u -> u.getSellingProducts().removeIf(p -> p.getBuyerLastName() == null));
        writeJsonIntoFile(users, PATH_OF_OUTPUT_USER_SOLD_PRODUCTS_JSON);
        return users;
    }


}
