package com.ginogipsy.magicbus.domain.enums;

import java.util.Arrays;
/**
 * @author ginogipsy
 */
public enum BaseEnum {

    UNDEFINED         ( "UNDEFINED"),
    BIANCA            ( "BIANCA"),
    ROSSA             ( "ROSSA"),
    ALTRO             ( "ALTRO");


    private final String description;

    BaseEnum(final String description){
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static BaseEnum getBase(final String description){
        return Arrays.stream(BaseEnum.values())
                .filter(v -> v.getDescription().equals(description))
                .findAny()
                .orElse(UNDEFINED);
    }

    @Override
    public String toString() {
        return "Base{" +
                "description='" + description + '\'' +
                '}';
    }
}

