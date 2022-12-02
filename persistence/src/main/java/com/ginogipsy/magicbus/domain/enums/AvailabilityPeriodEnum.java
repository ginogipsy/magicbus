package com.ginogipsy.magicbus.domain.enums;

import java.util.Arrays;

/**
 * @author ginogipsy
 */
public enum AvailabilityPeriodEnum {

    UNDEFINED         ( "UNDEFINED"),
    INVERNALE         ( "INVERNALE"),
    AUTUNNALE         ( "AUTUNNALE"),
    ESTIVO            ( "ESTIVO"),
    PRIMAVERILE       ( "PRIMAVERILE"),
    SPECIALE          ( "SPECIALE"),
    SEMPRE            ( "SEMPRE");


    private final String description;

    AvailabilityPeriodEnum(final String description){
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static AvailabilityPeriodEnum getAvailabilityPeriod(final String description){
        return Arrays.stream(AvailabilityPeriodEnum.values())
                .filter(v -> v.getDescription().equals(description))
                .findAny()
                .orElse(UNDEFINED);
    }

    @Override
    public String toString() {
        return "AvailabilityPeriod{" +
                "description='" + description + '\'' +
                '}';
    }
}
