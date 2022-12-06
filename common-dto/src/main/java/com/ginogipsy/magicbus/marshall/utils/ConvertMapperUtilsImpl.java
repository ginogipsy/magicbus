package com.ginogipsy.magicbus.marshall.utils;

import com.ginogipsy.magicbus.domain.Dough;
import com.ginogipsy.magicbus.domain.Fried;
import com.ginogipsy.magicbus.domain.Ingredient;
import com.ginogipsy.magicbus.domain.Topping;
import com.ginogipsy.magicbus.dto.DoughDTO;
import com.ginogipsy.magicbus.dto.FriedDTO;
import com.ginogipsy.magicbus.dto.IngredientDTO;
import com.ginogipsy.magicbus.dto.ToppingDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author ginogipsy
 */

@Slf4j
@Component
@RequiredArgsConstructor
public class ConvertMapperUtilsImpl implements ConvertMapperUtils {
    @Override
    public Optional<Topping> convertTopping(ToppingDTO toppingDTO) {
        return Optional.empty();
    }

    @Override
    public Optional<Ingredient> convertIngredient(IngredientDTO ingredientDTO) {
        return Optional.empty();
    }

    @Override
    public Optional<Fried> convertFried(FriedDTO friedDTO) {
        return Optional.empty();
    }

    @Override
    public Optional<Dough> takeDough(DoughDTO doughDTO) {
        return Optional.empty();
    }
}
