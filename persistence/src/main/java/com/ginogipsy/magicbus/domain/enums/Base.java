package com.ginogipsy.magicbus.domain.enums;

import java.util.Arrays;
/**
 * @author ginogipsy
 */
public enum Base {

    UNDEFINED         ( "UNDEFINED"),
    BIANCA            ( "BIANCA"),
    ROSSA             ( "ROSSA"),
    ALTRO             ( "ALTRO");


    private final String description;

    Base(final String description){
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static Base getBase(final String description){
        return Arrays.stream(Base.values()).filter(v -> v.getDescription().equals(description)).findAny().orElse(UNDEFINED);
    }

    @Override
    public String toString() {
        return "Base{" +
                "description='" + description + '\'' +
                '}';
    }
}

