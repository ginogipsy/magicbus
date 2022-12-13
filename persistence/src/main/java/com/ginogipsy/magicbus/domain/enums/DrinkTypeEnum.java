package com.ginogipsy.magicbus.domain.enums;

import java.util.Arrays;
/**
 * @author ginogipsy
 */
public enum DrinkTypeEnum {

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

    DrinkTypeEnum(final String description){
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static DrinkTypeEnum getDrinkType(final String description){
        return Arrays.stream(DrinkTypeEnum.values())
                .filter(v -> v.getDescription().equals(description))
                .findAny()
                .orElse(UNDEFINED);
    }

    @Override
    public String toString() {
        return "DrinkType{" +
                "description='" + description + '\'' +
                '}';
    }
}
