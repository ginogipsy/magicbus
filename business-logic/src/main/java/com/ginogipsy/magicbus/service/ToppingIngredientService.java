package com.ginogipsy.magicbus.service;

import com.ginogipsy.magicbus.dto.IngredientDTO;
import com.ginogipsy.magicbus.dto.ToppingDTO;
import com.ginogipsy.magicbus.dto.ToppingIngredientDTO;

import java.util.List;

public interface ToppingIngredientService {

    ToppingIngredientDTO insertIngredient(final String toppingName, final String ingredientName);

    List<String> insertIngredients(final String toppingName, final List<String> ingredientList);

    List<IngredientDTO> findByTopping(final String toppingName);

    List<ToppingDTO> findByIngredient(final String ingredientName);

    String deleteByToppingAndIngredient(final String toppingName, final String ingredientName);

}
