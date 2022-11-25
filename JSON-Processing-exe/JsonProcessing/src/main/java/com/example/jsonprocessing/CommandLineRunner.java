package com.example.jsonprocessing;

import com.example.jsonprocessing.DTOs.category.CategoryFullInformationDto;
import com.example.jsonprocessing.DTOs.user.UserWithSoldProductsDto;
import com.example.jsonprocessing.DTOs.user.UserWrapperDto;
import com.example.jsonprocessing.entities.Category;
import com.example.jsonprocessing.services.category.CategoryService;
import com.example.jsonprocessing.services.product.ProductService;
import com.example.jsonprocessing.services.seed.SeedService;
import com.example.jsonprocessing.services.user.UserService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Component
public class CommandLineRunner implements org.springframework.boot.CommandLineRunner {
    private final SeedService seedService;
    private final ProductService productService;
    private final UserService userService;
    private final CategoryService categoryService;

    public CommandLineRunner(SeedService seedService, ProductService productService, UserService userService, CategoryService categoryService) {
        this.seedService = seedService;
        this.productService = productService;
        this.userService = userService;
        this.categoryService = categoryService;
    }
    @Transactional
    @Override
    public void run(String... args) throws Exception {
//      this.seedService.seedAll();
//        Set<ProductPriceAndSellerFullNameDto> allUnSoldProducts = this.productService.
//                getAllUnSoldProducts(BigDecimal.valueOf(500), BigDecimal.valueOf(1000));
//
//        Set<UserWithSoldProductsDto> test = userService.findAllBySellingProductsBuyerIsNotNullOrderBySellingProductsBuyerFirstName();


//        Set<CategoryFullInformationDto> categoriesFullInfo = categoryService.getCategoriesFullInfo();
        UserWrapperDto userWrapperDto = userService.getUserWrapperDto();
        System.out.println();



    }
}
