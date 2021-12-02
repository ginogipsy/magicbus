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

    private final String descrizione;

    Status(final String descrizione){
        this.descrizione = descrizione;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public static Status getStatus(final String descrizione){
        return Arrays.stream(Status.values()).filter(v -> v.getDescrizione().equals(descrizione)).findAny().orElse(UNDEFINED);
    }

    @Override
    public String toString() {
        return "Status{" +
                "descrizione='" + descrizione + '\'' +
                '}';
    }
}
