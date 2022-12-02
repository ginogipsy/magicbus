package com.ginogipsy.magicbus.domain.enums;

import java.util.Arrays;
/**
 * @author ginogipsy
 */
public enum BeerTypeEnum {

    UNDEFINED                   ( "UNDEFINED"),
    PILSNER                     ( "PILSNER"),
    WEISSE                      ( "WEISSE"),
    ROSSA                       ( "ROSSA"),
    IPA                         ( "IPA"),
    APA                         ( "APA"),
    DOPPIO_MALTO                ( "DOPPIO_MALTO"),
    STOUT                       ( "STOUT");

    private final String description;

    BeerTypeEnum(final String description){

        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static BeerTypeEnum getBeerType(final String description){
        return Arrays.stream(BeerTypeEnum.values())
                .filter(v -> v.getDescription().equals(description))
                .findAny()
                .orElse(UNDEFINED);
    }

    @Override
    public String toString() {
        return "BeerType{" +
                "description='" + description + '\'' +
                '}';
    }
}
