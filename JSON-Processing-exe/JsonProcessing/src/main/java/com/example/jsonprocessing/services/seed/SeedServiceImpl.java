package com.example.jsonprocessing.services.seed;

import com.example.jsonprocessing.DTOs.category.ImportCategoryDto;
import com.example.jsonprocessing.DTOs.product.ImportProductDto;
import com.example.jsonprocessing.DTOs.user.ImportUserDto;
import com.example.jsonprocessing.entities.Category;
import com.example.jsonprocessing.entities.Product;
import com.example.jsonprocessing.entities.User;
import com.example.jsonprocessing.repos.CategoryRepository;
import com.example.jsonprocessing.repos.ProductRepository;
import com.example.jsonprocessing.repos.UserRepository;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import static com.example.jsonprocessing.constants.Paths.*;
import static com.example.jsonprocessing.constants.Utils.GSON;
import static com.example.jsonprocessing.constants.Utils.MAPPER;

@Service
public class SeedServiceImpl implements SeedService {
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public SeedServiceImpl(UserRepository userRepository, CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    @Override
    public void seedUser() throws FileNotFoundException {
        if (userRepository.count() == 0) {
            final FileReader fileReader = new FileReader(PATH_OF_USER_JSON.toFile());

            List<User> users = Arrays.stream(GSON.fromJson(fileReader, ImportUserDto[].class))
                    .map(importUserDto -> MAPPER.map(importUserDto, User.class))
                    .toList();
            userRepository.saveAllAndFlush(users);
        }

    }

    @Override
    public void seedCategory() throws IOException {
        if (categoryRepository.count() == 0) {
            final FileReader fileReader = new FileReader(PATH_OF_CATEGORY_JSON.toFile());
            List<Category> categories = Arrays.stream(GSON.fromJson(fileReader, ImportCategoryDto[].class))
                    .map(importCategoryDto -> MAPPER.map(importCategoryDto, Category.class))
                    .collect(Collectors.toList());

            categoryRepository.saveAllAndFlush(categories);
            fileReader.close();
        }
    }

    @Override
    public void seedProducts() throws IOException {
        if (productRepository.count() == 0) {
            FileReader fileReader = new FileReader(PATH_OF_PRODUCT_JSON.toFile());
            List<Product> products = Arrays.stream(GSON.fromJson(fileReader, ImportProductDto[].class))
                    .map(importProductDto -> MAPPER.map(importProductDto, Product.class))
                    .map(this::setRandomSeller)
                    .map(this::setRandomCategory)
                    .map(this::setRandomBuyer)
                    .toList();
            productRepository.saveAllAndFlush(products);
            fileReader.close();
        }
    }

    public Product setRandomCategory(Product product) {
        Random random = new Random();
        long high = categoryRepository.count();
        long numberOfCategories = random.nextLong(high);
        Set<Category> categories = new HashSet<>();
        for (int i = 1; i <= numberOfCategories; i++) {
            Category randomCategory = categoryRepository.getRandomCategory();
            categories.add(randomCategory);;
        }
        product.setCategories(categories);

        return product;
    }
    public Product setRandomSeller(Product product){
        User randomUser = userRepository.getRandomUser();
        product.setSeller(randomUser);
        return product;
    }

    public Product setRandomBuyer(Product product) {
        if(product.getPrice().compareTo(BigDecimal.valueOf(700)) > 0) {
            User buyer = userRepository.getRandomUser();

            while (buyer.equals(product.getSeller())) {
                buyer = userRepository.getRandomUser();
            }
            product.setBuyer(buyer);
        }
        return product;
    }
}
