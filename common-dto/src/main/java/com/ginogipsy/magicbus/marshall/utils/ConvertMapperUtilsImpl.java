package com.ginogipsy.magicbus.marshall.utils;

import com.ginogipsy.magicbus.domain.Dough;
import com.ginogipsy.magicbus.domain.Fried;
import com.ginogipsy.magicbus.domain.Ingredient;
import com.ginogipsy.magicbus.domain.Topping;
import com.ginogipsy.magicbus.dto.DoughDTO;
import com.ginogipsy.magicbus.dto.FriedDTO;
import com.ginogipsy.magicbus.dto.IngredientDTO;
import com.ginogipsy.magicbus.dto.ToppingDTO;
import com.ginogipsy.magicbus.marshall.DoughMapper;
import com.ginogipsy.magicbus.marshall.FriedMapper;
import com.ginogipsy.magicbus.marshall.IngredientMapper;
import com.ginogipsy.magicbus.marshall.ToppingMapper;
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

    private final ToppingMapper toppingMapper;
    private final IngredientMapper ingredientMapper;
    private final FriedMapper friedMapper;
    private final DoughMapper doughMapper;
    @Override
    public Optional<Topping> convertTopping(final ToppingDTO toppingDTO) {
        log.info("ConvertMapperUtilsImpl - takeTopping() -> Verifying toppingDTO..");
        return Optional.ofNullable(toppingDTO)
                .map(toppingMapper::convertToEntity);
    }

    @Override
    public Optional<Ingredient> convertIngredient(final IngredientDTO ingredientDTO) {
        log.info("ConvertMapperUtilsImpl - takeIngredient() -> Verifying ingredientDTO..");
        return Optional.ofNullable(ingredientDTO)
                .map(ingredientMapper::convertToEntity);
    }

    @Override
    public Optional<Fried> convertFried(final FriedDTO friedDTO) {
        log.info("ConvertMapperUtilsImpl - takeFried() -> Verifying friedDTO..");
        return Optional.ofNullable(friedDTO)
                .map(friedMapper::convertToEntity);
    }

    @Override
    public Optional<Dough> convertDough(final DoughDTO doughDTO) {
        log.info("ConvertMapperUtilsImpl - takeDough() -> Verifying doughDTO..");
        return Optional.ofNullable(doughDTO)
                .map(doughMapper::convertToEntity);
    }
}
