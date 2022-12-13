package com.ginogipsy.magicbus.domain.enums;

import java.util.Arrays;
/**
 * @author ginogipsy
 */
public enum StatusEnum {

    UNDEFINED                   ( "UNDEFINED"),
    INSERITO                    ( "INSERITO"),
    APPROVATO                   ( "APPROVATO"),
    RIFIUTATO                   ( "RIFIUTATO"),
    TEMPORANEAMENTE_BLOCCATO    ( "TEMPORANEAMENTE_BLOCCATO"),
    BLOCCATO                    ( "BLOCCATO"),
    DISPONIBILE                 ( "DISPONIBILE"),
    NON_DISPONIBILE             ( "NON_DISPONIBILE");

    private final String description;

    StatusEnum(final String description){
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static StatusEnum getStatus(final String description){
        return Arrays.stream(StatusEnum.values())
                .filter(v -> v.getDescription().equals(description))
                .findAny()
                .orElse(UNDEFINED);
    }

    @Override
    public String toString() {
        return "Status{" +
                "description='" + description + '\'' +
                '}';
    }
}
