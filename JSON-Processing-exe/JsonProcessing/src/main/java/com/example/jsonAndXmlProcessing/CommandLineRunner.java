package com.example.jsonAndXmlProcessing;

import com.example.jsonAndXmlProcessing.DTOsXML.product.ProductsInRangeWrapper;
import com.example.jsonAndXmlProcessing.DTOsXML.user.UserSoldProductsWrapper;
import com.example.jsonAndXmlProcessing.services.category.CategoryService;
import com.example.jsonAndXmlProcessing.services.product.ProductService;
import com.example.jsonAndXmlProcessing.services.seed.SeedService;
import com.example.jsonAndXmlProcessing.services.user.UserService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.bind.JAXBContext;
import java.math.BigDecimal;

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
        this.seedService.seedAll();

        //JSON ----->
//        Set<ProductPriceAndSellerFullNameDto> allUnSoldProducts = this.productService.
//             getAllUnSoldProducts(BigDecimal.valueOf(500), BigDecimal.valueOf(1000));
//        Set<UserWithSoldProductsDto> test = userService.findAllBySellingProductsBuyerIsNotNullOrderBySellingProductsBuyerFirstName();
//        Set<CategoryFullInformationDto> categoriesFullInfo = categoryService.getCategoriesFullInfo();
//        UserWrapperDto userWrapperDto = userService.getUserWrapperDto();
//        System.out.println();
        //XML ------->
//        ProductsInRangeWrapper allUnSoldProductsXml = this.productService.getAllUnSoldProductsXml(BigDecimal.valueOf(500), BigDecimal.valueOf(1000));
        UserSoldProductsWrapper wrapper = this.userService.findAllBySellingProductsWithBuyer();
        System.out.println();


    }
}
