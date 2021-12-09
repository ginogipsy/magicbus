package com.ginogipsy.magicbus.domain.enums;

import java.util.Arrays;

public enum DrinkType {

    UNDEFINED                   ( "UNDEFINED"),
    ACQUA                       ( "ACQUA"),
    COLA                        ( "COLA"),
    BIRRA                       ( "BIRRA"),
    VINO                        ( "VINO"),
    ANALCOLICO                  ( "ANALCOLICO"),
    AMARO                       ( "AMARO"),
    GRAPPA                      ( "GRAPPA"),
    ARANCIATA                   ( "ARANCIATA"),
    CHINOTTO                    ( "CHINOTTO"),
    CAFFE                       ( "CAFFE"),
    SUPERALCOLICO               ( "SUPERALCOLICO");

    private final String description;

    DrinkType(final String description){
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static DrinkType getDrinkType(final String description){
        return Arrays.stream(DrinkType.values()).filter(v -> v.getDescription().equals(description)).findAny().orElse(UNDEFINED);
    }

    @Override
    public String toString() {
        return "DrinkType{" +
                "description='" + description + '\'' +
                '}';
    }
}
