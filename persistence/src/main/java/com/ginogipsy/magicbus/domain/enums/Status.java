package com.ginogipsy.magicbus.domain.enums;

import java.util.Arrays;

public enum Status {

    UNDEFINED                   ( "UNDEFINED"),
    INSERITO                    ( "INSERITO"),
    APPROVATO                   ( "APPROVATO"),
    RIFIUTATO                   ( "RIFIUTATO"),
    TEMPORANEAMENTE_BLOCCATO    ( "TEMPORANEAMENTE_BLOCCATO"),
    BLOCCATO                    ( "BLOCCATO"),
    DISPONIBILE                 ( "DISPONIBILE"),
    NON_DISPONIBILE             ( "NON_DISPONIBILE");

    private final String description;

    Status(final String description){
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static Status getStatus(final String description){
        return Arrays.stream(Status.values()).filter(v -> v.getDescription().equals(description)).findAny().orElse(UNDEFINED);
    }

    @Override
    public String toString() {
        return "Status{" +
                "description='" + description + '\'' +
                '}';
    }
}
