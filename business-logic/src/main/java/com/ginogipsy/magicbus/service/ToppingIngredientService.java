package com.ginogipsy.magicbus.service;

import com.ginogipsy.magicbus.dto.IngredientDTO;
import com.ginogipsy.magicbus.dto.ToppingDTO;
import com.ginogipsy.magicbus.dto.ToppingIngredientDTO;

import java.util.List;

public interface ToppingIngredientService {

    ToppingIngredientDTO save(final String toppingName, final String ingredientName);

    List<IngredientDTO> findByTopping(final ToppingDTO toppingDTO);

    List<ToppingDTO> findByIngredient(final IngredientDTO ingredientDTO);
}
