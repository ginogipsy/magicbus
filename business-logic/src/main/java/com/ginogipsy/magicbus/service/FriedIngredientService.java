package com.ginogipsy.magicbus.service;

import com.ginogipsy.magicbus.dto.FriedDTO;
import com.ginogipsy.magicbus.dto.FriedIngredientDTO;
import com.ginogipsy.magicbus.dto.IngredientDTO;

import java.util.List;

public interface FriedIngredientService {

    FriedIngredientDTO save(final String friedName, final String ingredientName);

    List<IngredientDTO> findByFried(final FriedDTO friedDTO);

    List<FriedDTO> findByIngredient(final IngredientDTO ingredientDTO);
}
