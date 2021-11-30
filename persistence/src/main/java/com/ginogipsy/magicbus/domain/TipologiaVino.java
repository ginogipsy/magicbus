package com.ginogipsy.magicbus.domain;

import java.util.Arrays;

public enum TipologiaVino {

    UNDEFINED                   (0, "UNDEFINED"),
    ROSSO                       (1, "ROSSO"),
    BIANCO                      (2, "BIANCO");

    private final int id;
    private final String descrizione;

    TipologiaVino(final int id, final String descrizione){
        this.id = id;
        this.descrizione = descrizione;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public int getId() {
        return id;
    }

    public static TipologiaVino getTipologiaVino(final int id){
        return Arrays.stream(TipologiaVino.values()).filter(v -> v.getId() == id).findAny().orElse(UNDEFINED);
    }

    public static TipologiaVino getTipologiaVino(final String descrizione){
        return Arrays.stream(TipologiaVino.values()).filter(v -> v.getDescrizione().equals(descrizione)).findAny().orElse(UNDEFINED);
    }
}
