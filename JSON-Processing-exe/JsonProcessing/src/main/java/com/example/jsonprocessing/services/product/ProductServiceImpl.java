package com.example.jsonprocessing.services.product;

import com.example.jsonprocessing.DTOs.product.ProductPriceAndSellerFullNameDto;
import com.example.jsonprocessing.entities.Product;
import com.example.jsonprocessing.repos.ProductRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Collectors;

import static com.example.jsonprocessing.constants.Paths.PATH_OF_OUTPUT_PRODUCTS_WITH_SELLER_AND_PRICE_JSON;
import static com.example.jsonprocessing.constants.Utils.*;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }



    @Override
    public Product getRandomProduct() {
       return productRepository.getRandomProduct();
    }

    @Override
    public Set<ProductPriceAndSellerFullNameDto> getAllUnSoldProducts(BigDecimal low, BigDecimal high) throws IOException {
        Set<Product> products = productRepository.findAllByBuyerIsNullAndPriceBetweenOrderByPrice(low, high);
        Set<ProductPriceAndSellerFullNameDto> resultSet = products.stream().map(product -> MAPPER.map(product, ProductPriceAndSellerFullNameDto.class))
                .collect(Collectors.toSet());
        writeJsonIntoFile(resultSet,PATH_OF_OUTPUT_PRODUCTS_WITH_SELLER_AND_PRICE_JSON);
        System.out.println();
        return  resultSet;

    }
}
