package com.ginogipsy.magicbus.domain;

import java.util.Arrays;

public enum Status {

    UNDEFINED                   (0, "UNDEFINED"),
    INSERITO                    (1, "INSERITO"),
    APPROVATO                   (2, "APPROVATO"),
    RIFIUTATO                   (3, "RIFIUTATO"),
    TEMPORANEAMENTE_BLOCCATO    (4, "TEMPORANEAMENTE_BLOCCATO"),
    BLOCCATO                    (5, "BLOCCATO"),
    DISPONIBILE                 (6, "DISPONIBILE"),
    NON_DISPONIBILE             (7, "NON_DISPONIBILE");

    private final int id;
    private final String descrizione;

    Status(final int id, final String descrizione){
        this.id = id;
        this.descrizione = descrizione;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public int getId() {
        return id;
    }

    public static Status getStatus(final int id){
        return Arrays.stream(Status.values()).filter(v -> v.getId() == id).findAny().orElse(UNDEFINED);
    }

    public static Status getStatus(final String descrizione){
        return Arrays.stream(Status.values()).filter(v -> v.getDescrizione().equals(descrizione)).findAny().orElse(UNDEFINED);
    }
}
