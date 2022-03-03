package com.ginogipsy.magicbus.configuration;

import com.ginogipsy.magicbus.domain.Ingredient;
import org.modelmapper.AbstractConverter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class IngredientToIngredientNameConverter extends AbstractConverter<Set<Ingredient>, List<String>> {
    @Override
    protected List<String> convert(Set<Ingredient> ingredients) {
        return Optional.ofNullable(ingredients).map(i -> i.stream().map(Ingredient::getName).toList()).orElse(new ArrayList<>());
    }
}
