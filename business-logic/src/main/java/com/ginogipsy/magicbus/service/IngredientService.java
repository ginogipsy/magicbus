package com.ginogipsy.magicbus.service;

import com.ginogipsy.magicbus.dto.IngredientDTO;

import java.util.List;

public interface IngredientService {

    IngredientDTO findByName(final String name);

    List<IngredientDTO> findByNameContains(final String name);

    IngredientDTO save(final IngredientDTO ingredientDTO);
}
