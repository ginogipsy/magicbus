package com.ginogipsy.magicbus.service;

import com.ginogipsy.magicbus.dto.DoughDTO;
import com.ginogipsy.magicbus.dto.DoughIngredientDTO;
import com.ginogipsy.magicbus.dto.IngredientDTO;

import java.util.List;

public interface DoughIngredientService {

    DoughIngredientDTO insertIngredient(final String doughName, final String ingredientName);

    List<String> insertIngredients(final String doughName, final List<String> ingredientsName);

    List<IngredientDTO> findByDough(final String doughName);

    List<DoughDTO> findByIngredient(final String ingredientName);

    String deleteByDoughAndIngredient(final String doughName, final String ingredientList);
}
