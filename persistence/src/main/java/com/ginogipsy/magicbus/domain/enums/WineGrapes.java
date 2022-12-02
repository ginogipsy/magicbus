package com.ginogipsy.magicbus.domain.enums;

import java.util.Arrays;
/**
 * @author ginogipsy
 */
public enum WineGrapes {

    UNDEFINED                   ( "UNDEFINED"),
    ROSSO                       ( "ROSSO"),
    BIANCO                      ( "BIANCO");

    private final String description;

    WineGrapes(final String description){
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static WineGrapes getWineGrapes(final String description){
        return Arrays.stream(WineGrapes.values()).filter(v -> v.getDescription().equals(description)).findAny().orElse(UNDEFINED);
    }

    @Override
    public String toString() {
        return "WineGrapes{" +
                "description='" + description + '\'' +
                '}';
    }
}
