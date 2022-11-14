package com.example.advquerying.services.ingredient;

import com.example.advquerying.entities.Ingredient;
import com.example.advquerying.repositories.IngredientRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class IngredientsServiceImpl implements IngredientService{
    private IngredientRepository ingredientRepository;

    public IngredientsServiceImpl(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public List<Ingredient> getAllIngredientsByName(String name) {
        return ingredientRepository.findAllByNameStartingWith(name);
    }

    @Override
    public List<Ingredient> getAllIngredientsIn(List<String> strings) {
      return   ingredientRepository.findAllByNameInOrderByPrice(strings);
    }

    @Override
    public void updateIngredientsPrice(BigDecimal increase) {
        ingredientRepository.updateAllIngredientsPrice(increase);
    }

    @Override
    public void updateIngredientsPriceByNames(List<String> names, BigDecimal increase) {
        ingredientRepository.updateAllIngredientsPriceWithNameIn(names,increase);
    }
}
