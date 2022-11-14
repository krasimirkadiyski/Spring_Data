package com.example.advquerying.services.label;

import com.example.advquerying.repositories.IngredientRepository;
import org.springframework.stereotype.Service;

@Service
public class LabelServiceImpl implements LabelService{
    private IngredientRepository ingredientRepository;

    public LabelServiceImpl(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }
}
