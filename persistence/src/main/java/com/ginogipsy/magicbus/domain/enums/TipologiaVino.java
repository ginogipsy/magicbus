package com.ginogipsy.magicbus.domain.enums;

import java.util.Arrays;

public enum TipologiaVino {

    UNDEFINED                   ( "UNDEFINED"),
    ROSSO                       ( "ROSSO"),
    BIANCO                      ( "BIANCO");

    private final String descrizione;

    TipologiaVino(final String descrizione){
        this.descrizione = descrizione;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public static TipologiaVino getTipologiaVino(final String descrizione){
        return Arrays.stream(TipologiaVino.values()).filter(v -> v.getDescrizione().equals(descrizione)).findAny().orElse(UNDEFINED);
    }

    @Override
    public String toString() {
        return "TipologiaVino{" +
                "descrizione='" + descrizione + '\'' +
                '}';
    }
}
