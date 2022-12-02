package com.ginogipsy.magicbus.domain.enums;

import java.util.Arrays;
/**
 * @author ginogipsy
 */
public enum MeasureUnit {

    UNDEFINED         ( "UNDEFINED"),
    KG                ( "KG"),
    G                 ( "G"),
    LT                ( "LT"),
    CL                ( "CL"),
    ML                ( "ML"),
    PEZZI             ( "PEZZI"),
    GOCCE             ( "GOCCE"),
    CUCCHIAI          ( "CUCCHIAI"),
    CUCCHIAINI        ( "CUCCHIAINI"),
    TAZZE             ( "TAZZE");


    private final String description;

    MeasureUnit(final String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static MeasureUnit getMeasureUnit(final String description){
        return Arrays.stream(MeasureUnit.values()).filter(v -> v.getDescription().equals(description)).findAny().orElse(UNDEFINED);
    }
}
