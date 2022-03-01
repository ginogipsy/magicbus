package com.ginogipsy.magicbus.service;

import com.ginogipsy.magicbus.dto.DoughDTO;
import com.ginogipsy.magicbus.dto.DoughIngredientDTO;
import com.ginogipsy.magicbus.dto.IngredientDTO;

import java.util.List;

public interface DoughIngredientService {

    DoughIngredientDTO save(final String doughName, final String ingredientName);

    List<IngredientDTO> findByDough(final DoughDTO doughDTO);

    List<DoughDTO> findByIngredient(final IngredientDTO ingredientDTO);
}
