package com.ginogipsy.magicbus.marshall.utils;

import com.ginogipsy.magicbus.domain.Dough;
import com.ginogipsy.magicbus.domain.Fried;
import com.ginogipsy.magicbus.domain.Ingredient;
import com.ginogipsy.magicbus.domain.Topping;
import com.ginogipsy.magicbus.dto.DoughDTO;
import com.ginogipsy.magicbus.dto.FriedDTO;
import com.ginogipsy.magicbus.dto.IngredientDTO;
import com.ginogipsy.magicbus.dto.ToppingDTO;


import java.util.Optional;

/**
 * @author ginogipsy
 */

public interface ConvertMapperUtils {

    Optional<Topping> convertTopping(final ToppingDTO toppingDTO);
    Optional<Ingredient> convertIngredient(final IngredientDTO ingredientDTO);
    Optional<Fried> convertFried(final FriedDTO friedDTO);
    Optional<Dough> takeDough(final DoughDTO doughDTO);
}
