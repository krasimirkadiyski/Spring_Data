package com.example.advquerying.services.ingredient;

import com.example.advquerying.entities.Ingredient;

import java.math.BigDecimal;
import java.util.List;

public interface IngredientService {

   List<Ingredient> getAllIngredientsByName(String name);

    List<Ingredient> getAllIngredientsIn(List<String> strings);

    void updateIngredientsPrice(BigDecimal increase);

    void updateIngredientsPriceByNames(List<String> names, BigDecimal increase);

}
