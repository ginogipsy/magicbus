package com.ginogipsy.magicbus.domain.enums;

import java.util.Arrays;

public enum AvailabilityPeriod {

    UNDEFINED         ( "UNDEFINED"),
    INVERNALE         ( "INVERNALE"),
    AUTUNNALE         ( "AUTUNNALE"),
    ESTIVO            ( "ESTIVO"),
    PRIMAVERILE       ( "PRIMAVERILE"),
    SPECIALE          ( "SPECIALE"),
    SEMPRE            ( "SEMPRE");


    private final String description;

    AvailabilityPeriod(final String description){
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static AvailabilityPeriod getAvailabilityPeriod(final String description){
        return Arrays.stream(AvailabilityPeriod.values()).filter(v -> v.getDescription().equals(description)).findAny().orElse(UNDEFINED);
    }

    @Override
    public String toString() {
        return "AvailabilityPeriod{" +
                "description='" + description + '\'' +
                '}';
    }
}
