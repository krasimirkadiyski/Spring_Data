package com.example.jsonprocessing.services.product;

import com.example.jsonprocessing.DTOs.product.ProductPriceAndSellerFullNameDto;
import com.example.jsonprocessing.entities.Product;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Set;

public interface ProductService {


   Product getRandomProduct();
   Set<ProductPriceAndSellerFullNameDto> getAllUnSoldProducts(BigDecimal low, BigDecimal high) throws IOException;

}
