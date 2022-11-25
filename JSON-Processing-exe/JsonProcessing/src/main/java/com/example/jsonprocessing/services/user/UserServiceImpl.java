package com.example.jsonprocessing.services.user;


import com.example.jsonprocessing.DTOs.user.UserDto;
import com.example.jsonprocessing.DTOs.user.UserLiteInfoDto;
import com.example.jsonprocessing.DTOs.user.UserWithSoldProductsDto;
import com.example.jsonprocessing.DTOs.user.UserWrapperDto;
import com.example.jsonprocessing.entities.User;
import com.example.jsonprocessing.repos.UserRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.example.jsonprocessing.constants.Paths.PATH_OF_OUTPUT_USER_SOLD_PRODUCTS_JSON;
import static com.example.jsonprocessing.constants.Paths.PATH_OF_OUTPUT_USER_WRAPPER_JSON;
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

    @Override
    public UserWrapperDto getUserWrapperDto() throws IOException {
        List<User> allUsers = userRepository.findAll();
        List<UserDto> allUsersDto = allUsers.stream()
                .map(u -> MAPPER.map(u, UserDto.class)).toList();
        List<UserLiteInfoDto> allUserLiteInfoDto = allUsersDto.stream()
                .map(UserDto::toUserLiteInfoDto)
                .sorted((u1, f2) -> Integer.compare(f2.getProductsSold().getCount(), u1.getProductsSold().getCount()))
                .toList();



        UserWrapperDto userWrapperDto = new UserWrapperDto(allUserLiteInfoDto);
        writeJsonIntoFile(List.of(userWrapperDto),PATH_OF_OUTPUT_USER_WRAPPER_JSON);
        return userWrapperDto;
    }


}
