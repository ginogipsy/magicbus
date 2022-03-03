package com.ginogipsy.magicbus.configuration;

import com.ginogipsy.magicbus.domain.Supplier;
import org.modelmapper.AbstractConverter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class SuppliersToSuppliersNameConverter extends AbstractConverter<Set<Supplier>, List<String>> {
    @Override
    protected List<String> convert(Set<Supplier> suppliers) {
        return Optional.ofNullable(suppliers).map(s -> s.stream().map(Supplier::getName).toList()).orElse(new ArrayList<>());
    }
}
