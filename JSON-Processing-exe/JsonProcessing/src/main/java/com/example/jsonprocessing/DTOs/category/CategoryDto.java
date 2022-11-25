package com.example.jsonprocessing.DTOs.category;

import com.example.jsonprocessing.DTOs.product.ProductDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Set;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {
    private String name;
    private Set<ProductDto> products;

    public int getProductsCount(){
        return this.products.size();
    }
    public double getAveragePrice(){
      return this.getTotalRevenue() / this.products.size();
    }
    public double getTotalRevenue(){
        double total = 0;
        for (ProductDto product : products) {
            total += product.getPrice().doubleValue();
        }
        return total;
    }
}
