package com.ginogipsy.magicbus.service;

import com.ginogipsy.magicbus.dto.IngredientDTO;

import java.util.List;

/**
 * @author ginogipsy
 */

public interface IngredientService {

    IngredientDTO findByName(final String name);

    List<IngredientDTO> findByNameContains(final String name);

    IngredientDTO insert(final IngredientDTO ingredientDTO);
}
