package com.ginogipsy.magicbus.configuration;

import com.ginogipsy.magicbus.domain.ToppingIngredient;
import org.modelmapper.AbstractConverter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * @author ginogipsy
 */

public class ToppingIngredientListToToppingNameListConverter extends AbstractConverter<Set<ToppingIngredient>, List<String>> {
    @Override
    protected List<String> convert(Set<ToppingIngredient> toppingIngredients) {
        return Optional.ofNullable(toppingIngredients)
                .map(tis -> tis.stream()
                        .map(ti -> ti.getTopping().getName())
                        .toList())
                .orElse(new ArrayList<>());
    }
}
