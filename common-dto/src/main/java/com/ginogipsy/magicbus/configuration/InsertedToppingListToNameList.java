package com.ginogipsy.magicbus.configuration;


import com.ginogipsy.magicbus.domain.Topping;
import org.modelmapper.AbstractConverter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class InsertedToppingListToNameList extends AbstractConverter<Set<Topping>, List<String>> {
    @Override
    protected List<String> convert(Set<Topping> toppings) {
        return Optional.ofNullable(toppings).map(t -> t.stream().map(Topping::getName).toList()).orElse(new ArrayList<>());
    }
}
