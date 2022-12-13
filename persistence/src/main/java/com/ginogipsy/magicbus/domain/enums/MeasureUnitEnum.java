package com.ginogipsy.magicbus.domain.enums;

import java.util.Arrays;
/**
 * @author ginogipsy
 */
public enum MeasureUnitEnum {

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

    MeasureUnitEnum(final String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static MeasureUnitEnum getMeasureUnit(final String description){
        return Arrays.stream(MeasureUnitEnum.values())
                .filter(v -> v.getDescription().equals(description))
                .findAny()
                .orElse(UNDEFINED);
    }
}
