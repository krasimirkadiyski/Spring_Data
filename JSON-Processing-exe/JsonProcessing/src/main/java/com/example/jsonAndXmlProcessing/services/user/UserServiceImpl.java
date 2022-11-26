package com.example.jsonAndXmlProcessing.services.user;


import com.example.jsonAndXmlProcessing.DTOsJSON.user.UserDto;
import com.example.jsonAndXmlProcessing.DTOsJSON.user.UserLiteInfoDto;
import com.example.jsonAndXmlProcessing.DTOsJSON.user.UserWithSoldProductsDto;
import com.example.jsonAndXmlProcessing.DTOsJSON.user.UserWrapperDto;
import com.example.jsonAndXmlProcessing.DTOsXML.product.ProductXmlDto;
import com.example.jsonAndXmlProcessing.DTOsXML.product.SoldProductWithBuyer;
import com.example.jsonAndXmlProcessing.DTOsXML.user.UserSoldProductsWrapper;
import com.example.jsonAndXmlProcessing.DTOsXML.user.UserXmlDto;
import com.example.jsonAndXmlProcessing.DTOsXML.user.XmlUserSoldProductDto;
import com.example.jsonAndXmlProcessing.entities.User;
import com.example.jsonAndXmlProcessing.repos.UserRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.example.jsonAndXmlProcessing.constants.Paths.PATH_OF_OUTPUT_USER_SOLD_PRODUCTS_JSON;
import static com.example.jsonAndXmlProcessing.constants.Paths.PATH_OF_OUTPUT_USER_WRAPPER_JSON;
import static com.example.jsonAndXmlProcessing.constants.Utils.MAPPER;
import static com.example.jsonAndXmlProcessing.constants.Utils.writeJsonIntoFile;

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
    public UserSoldProductsWrapper findAllBySellingProductsWithBuyer() {
        Set<User> users = userRepository.findAllByOrderByLastNameAscFirstNameAsc();
        Set<XmlUserSoldProductDto> userWithSoldProductsDto = users.stream().map(user -> MAPPER.map(user, XmlUserSoldProductDto.class))
                .collect(Collectors.toSet());
        userWithSoldProductsDto
                .forEach(u -> u.getSellingProducts()
                        .getProducts()
                        .removeIf(p -> p.getBuyerLastName() == null));


        System.out.println();
        return new UserSoldProductsWrapper(userWithSoldProductsDto);
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
