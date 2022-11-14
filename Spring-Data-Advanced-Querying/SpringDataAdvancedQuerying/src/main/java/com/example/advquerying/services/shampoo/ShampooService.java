package com.example.advquerying.services.shampoo;

import com.example.advquerying.entities.Ingredient;
import com.example.advquerying.entities.Shampoo;
import com.example.advquerying.entities.Size;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public interface ShampooService {
    Set<Shampoo> getAllShampooBySize(Size size);

    List<Shampoo> getAllShampooBySizeOrLabelId(Size size, long labelId);

    List<Shampoo> getAllShampooByPrice(BigDecimal price);

    int getCountOfShampoosPriceLessThan(BigDecimal price);

    List<Shampoo> getShampoosWithIngredients(List<String> ingredients);

    List<Shampoo> getShampoosByCountOfIngredients(int count);

    void deleteShampooByName(String sName);
}

