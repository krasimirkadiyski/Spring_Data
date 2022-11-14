package com.example.advquerying.repositories;

import com.example.advquerying.entities.Shampoo;
import com.example.advquerying.entities.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Repository
public interface ShampooRepository extends JpaRepository<Shampoo, Long> {
    Set<Shampoo> findAllBySizeOrderById(Size size);

    List<Shampoo> findAllBySizeOrLabelIdOrderByPrice(Size size, long labelId);

    List<Shampoo> findShampoosByPriceAfterOrderByPriceDesc(BigDecimal price);

    int countAllByPriceBefore(BigDecimal price);


    @Query("""
    SELECT s FROM Shampoo AS s
    JOIN s.ingredients AS i
    WHERE i.name IN :ingredients
""")
    List<Shampoo> findShampoosByIngredients(List<String> ingredients);

    @Query("""
    SELECT s FROM Shampoo AS s
    WHERE s.ingredients.size = :count
""")
    List<Shampoo> findShampoosByIngredientsCount(int count);

    @Query("""
DELETE FROM Shampoo AS s
WHERE s.brand LIKE :sName
""")
    @Transactional
    @Modifying
    void deleteShampooByBrand(String sName);
}
