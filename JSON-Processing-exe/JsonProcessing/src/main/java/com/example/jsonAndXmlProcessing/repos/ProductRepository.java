package com.example.jsonAndXmlProcessing.repos;

import com.example.jsonAndXmlProcessing.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Set;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {


    @Query(value = "select * from products order by RAND() limit 1" ,nativeQuery = true)
    Product getRandomProduct();

    @Transactional
    Set<Product> findAllByBuyerIsNullAndPriceBetweenOrderByPrice(BigDecimal low, BigDecimal high);



}
