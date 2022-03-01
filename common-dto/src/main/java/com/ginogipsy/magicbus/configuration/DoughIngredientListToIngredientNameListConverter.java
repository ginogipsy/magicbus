package com.ginogipsy.magicbus.configuration;

import com.ginogipsy.magicbus.domain.DoughIngredient;
import org.modelmapper.AbstractConverter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class DoughIngredientListToIngredientNameListConverter extends AbstractConverter<Set<DoughIngredient>, List<String>> {
    @Override
    protected List<String> convert(Set<DoughIngredient> doughIngredients) {
        return Optional.ofNullable(doughIngredients)
                .map(dis -> dis.stream()
                        .map(di -> di.getIngredient().getName())
                        .toList())
                .orElse(new ArrayList<>());
    }
}
