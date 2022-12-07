package com.ginogipsy.magicbus.service;

import com.ginogipsy.magicbus.dto.FriedDTO;
import com.ginogipsy.magicbus.dto.FriedIngredientDTO;
import com.ginogipsy.magicbus.dto.IngredientDTO;

import java.util.List;

/**
 * @author ginogipsy
 */

public interface FriedIngredientService {

    FriedIngredientDTO insertIngredient(final String friedName, final String ingredientName);

    List<String> insertIngredients(final String friedName, final List<String> ingredientList);

    List<IngredientDTO> findIngredientListByFried(final String friedName);

    List<FriedDTO> findFriedListByIngredient(final String ingredientName);

    String deleteByFriedAndIngredient(final String friedName, final String ingredientName);
}
