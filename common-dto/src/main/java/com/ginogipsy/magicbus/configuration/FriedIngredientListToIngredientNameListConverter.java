package com.ginogipsy.magicbus.configuration;

import com.ginogipsy.magicbus.domain.FriedIngredient;
import org.modelmapper.AbstractConverter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class FriedIngredientListToIngredientNameListConverter extends AbstractConverter<Set<FriedIngredient>, List<String>> {
    @Override
    protected List<String> convert(Set<FriedIngredient> friedIngredients) {
        return Optional.ofNullable(friedIngredients)
                .map(fis -> fis.stream()
                        .map(fi -> fi.getIngredient().getName())
                        .toList())
                .orElse(new ArrayList<>());
    }
}
