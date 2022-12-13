package com.ginogipsy.magicbus.domain.enums;

import java.util.Arrays;
/**
 * @author ginogipsy
 */
public enum IngredientTypeEnum {

    UNDEFINED                   ( "UNDEFINED"),
    CARNE                       ( "CARNE"),
    PESCE                       ( "PESCE"),
    FORMAGGIO                   ( "FORMAGGIO"),
    UOVA                        ( "UOVA"),
    SPEZIA                      ( "SPEZIA"),
    VEGETALE                    ( "VEGETALE"),
    FRUTTA                      ( "FRUTTA"),
    SALSA                       ( "SALSA"),
    MARMELLATA                  ( "MARMELLATA"),
    CONFETTURA                  ( "CONFETTURA"),
    CONFIT                      ( "CONFIT"),
    FORMAGGIO_VEGANO            ( "FORMAGGIO_VEGANO"),
    CARNE_VEGANA                ( "CARNE_VEGANA");


    private final String description;

    IngredientTypeEnum(final String description){

        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static IngredientTypeEnum getIngredientType(final String description){
        return Arrays.stream(IngredientTypeEnum.values())
                .filter(v -> v.getDescription().equals(description))
                .findAny()
                .orElse(UNDEFINED);
    }

    @Override
    public String toString() {
        return "IngredientType{" +
                "description='" + description + '\'' +
                '}';
    }
}
