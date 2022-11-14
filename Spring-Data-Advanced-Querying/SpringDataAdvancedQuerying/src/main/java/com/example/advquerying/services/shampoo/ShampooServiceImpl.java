package com.example.advquerying.services.shampoo;

import com.example.advquerying.entities.Shampoo;
import com.example.advquerying.entities.Size;
import com.example.advquerying.repositories.ShampooRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Service
public class ShampooServiceImpl implements  ShampooService{
    private ShampooRepository shampooRepository;

    public ShampooServiceImpl(ShampooRepository shampooRepository) {
        this.shampooRepository = shampooRepository;
    }

    @Override
    public Set<Shampoo> getAllShampooBySize(Size size) {
       return shampooRepository.findAllBySizeOrderById(size);
    }

    @Override
    public List<Shampoo> getAllShampooBySizeOrLabelId(Size size, long labelId) {
       return shampooRepository.findAllBySizeOrLabelIdOrderByPrice(size,labelId);
    }

    @Override
    public List<Shampoo> getAllShampooByPrice(BigDecimal price) {
       return shampooRepository.findShampoosByPriceAfterOrderByPriceDesc(price);
    }

    @Override
    public int getCountOfShampoosPriceLessThan(BigDecimal price) {
      return shampooRepository.countAllByPriceBefore(price);
    }

    @Override
    public List<Shampoo> getShampoosWithIngredients(List<String> ingredients) {
      return   shampooRepository.findShampoosByIngredients(ingredients);
    }

    @Override
    public List<Shampoo> getShampoosByCountOfIngredients(int count) {
        return shampooRepository.findShampoosByIngredientsCount(count);

    }

    @Override
    public void deleteShampooByName(String sName) {
        shampooRepository.deleteShampooByBrand(sName);

    }

}
