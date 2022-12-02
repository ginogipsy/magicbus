package com.ginogipsy.magicbus.domain.enums;

import java.util.Arrays;
/**
 * @author ginogipsy
 */
public enum ProductCategory {

    UNDEFINED         ("UNDEFINED"),
    PIZZA             ("PIZZA"),
    CALZONE           ("CALZONE"),
    PIZZA_FRITTA      ("PIZZA_FRITTA"),
    PANUOZZO          ("PANUOZZO"),
    PANINO            ("PANINO"),
    FRITTO            ("FRITTO"),
    BIBITA            ("BIBITA"),
    ARANCINO          ("ARANCINO"),
    FRITTO_CLASSICO   ("FRITTO CLASSICO");


    private final String description;

    ProductCategory(final String description){
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static ProductCategory getProductCategory(final String description){
        return Arrays.stream(ProductCategory.values())
                .filter(v -> v.getDescription().equals(description))
                .findAny()
                .orElse(UNDEFINED);
    }

    @Override
    public String toString() {
        return "ProductCategory{" +
                "description='" + description + '\'' +
                '}';
    }
}
