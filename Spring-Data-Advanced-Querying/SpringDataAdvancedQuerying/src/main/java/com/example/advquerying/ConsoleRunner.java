package com.example.advquerying;

import com.example.advquerying.entities.Ingredient;
import com.example.advquerying.entities.Shampoo;
import com.example.advquerying.entities.Size;
import com.example.advquerying.services.ingredient.IngredientService;
import com.example.advquerying.services.label.LabelService;
import com.example.advquerying.services.shampoo.ShampooService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private IngredientService ingredientService;
    private ShampooService shampooService;
    private LabelService labelService;

    @Autowired
    public ConsoleRunner(IngredientService ingredientService, ShampooService shampooService, LabelService labelService) {
        this.ingredientService = ingredientService;
        this.shampooService = shampooService;
        this.labelService = labelService;
    }

    @Override
    public void run(String... args) throws Exception {

        //1
//        Set<Shampoo> shampoosBySize = this.shampooService.getAllShampooBySize(Size.MEDIUM);
//        shampoosBySize.forEach(System.out::println);

        //2
//        List<Shampoo> allShampooBySizeOrLabelId = shampooService.getAllShampooBySizeOrLabelId(Size.MEDIUM, 10);
//        allShampooBySizeOrLabelId.forEach(System.out::println);
        //3
//        List<Shampoo> allShampooByPrice = shampooService.getAllShampooByPrice(BigDecimal.valueOf(5));
//        allShampooByPrice.forEach(System.out::println);
        //4
//        List<Ingredient> allIngredientsByName = ingredientService.getAllIngredientsByName("M");
//        allIngredientsByName.forEach(System.out::println);
        //5
//        List<String> ingredients = new ArrayList<>();
//        ingredients.add("Lavender");
//        ingredients.add("Apple");
//        ingredients.add("Herbs");
//        List<Ingredient> allIngredientsIn = ingredientService.getAllIngredientsIn(ingredients);
//        allIngredientsIn.forEach(System.out::println);
        //6
//        int count = shampooService.getCountOfShampoosPriceLessThan(BigDecimal.valueOf(8.50));
//        System.out.println(count);
        //7
//        List<String> ingredients = new ArrayList<>();
//        ingredients.add("Berry");
//        ingredients.add("Mineral-Collagen");
//        List<Shampoo> shampoosWithIngredients = shampooService.getShampoosWithIngredients(ingredients);
//        shampoosWithIngredients.forEach(System.out::println);
        //8
//        List<Shampoo> shampoosByCountOfIngredients = shampooService.getShampoosByCountOfIngredients(2);
//        shampoosByCountOfIngredients.forEach(System.out::println);
        //9
//        shampooService.deleteShampooByName("Active-Caffeine");
        //10
//            ingredientService.updateIngredientsPrice(BigDecimal.valueOf(1.1));
        //11
            List<String> names = new ArrayList<>();
            names.add("Superfruit Nutrition");
            names.add("Cotton Fresh");
            ingredientService.updateIngredientsPriceByNames(names,BigDecimal.valueOf(1.1));



    }
}
