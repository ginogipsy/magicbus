package com.ginogipsy.magicbus.domain.enums;

import java.util.Arrays;
/**
 * @author ginogipsy
 */
public enum WineQuality {

    UNDEFINED         ( "UNDEFINED"),
    PINOT_GRIGIO      ( "PINOT_GRIGIO"),
    PECORINO          ( "PECORINO"),
    FALANGHINA        ( "FALANGHINA"),
    CABERNET          ( "CABERNET"),
    PINOT_NERO        ( "PINOT_NERO");

    private final String description;

    WineQuality(final String description){

        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static WineQuality getWineQuality(final String description){
        return Arrays.stream(WineQuality.values()).filter(v -> v.getDescription().equals(description)).findAny().orElse(UNDEFINED);
    }

    @Override
    public String toString() {
        return "WineQuality{" +
                "description='" + description + '\'' +
                '}';
    }
}