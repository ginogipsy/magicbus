package com.ginogipsy.magicbus.domain.enums;

import java.util.Arrays;

public enum BeerType {

    UNDEFINED                   ( "UNDEFINED"),
    PILSNER                     ( "PILSNER"),
    WEISSE                      ( "WEISSE"),
    ROSSA                       ( "ROSSA"),
    IPA                         ( "IPA"),
    APA                         ( "APA"),
    DOPPIO_MALTO                ( "DOPPIO_MALTO"),
    STOUT                       ( "STOUT");

    private final String description;

    BeerType(final String description){

        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static BeerType getBeerType(final String description){
        return Arrays.stream(BeerType.values()).filter(v -> v.getDescription().equals(description)).findAny().orElse(UNDEFINED);
    }

    @Override
    public String toString() {
        return "BeerType{" +
                "description='" + description + '\'' +
                '}';
    }
}
