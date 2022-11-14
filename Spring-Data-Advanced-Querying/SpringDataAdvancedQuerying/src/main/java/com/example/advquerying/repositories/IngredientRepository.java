package com.example.advquerying.repositories;

import com.example.advquerying.entities.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface IngredientRepository extends JpaRepository <Ingredient,Long> {

    List<Ingredient> findAllByNameStartingWith(String name);

    List<Ingredient> findAllByNameInOrderByPrice(List<String> ingredients);

    @Modifying
    @Transactional
    @Query("""
UPDATE Ingredient as i
SET i.price = i.price * :increase
""")
    void updateAllIngredientsPrice(BigDecimal increase);


    @Modifying
    @Transactional
    @Query("""
UPDATE Ingredient as i
SET i.price = i.price * :increase
WHERE i.name IN :names
""")
    void updateAllIngredientsPriceWithNameIn(List<String> names, BigDecimal increase);
}
