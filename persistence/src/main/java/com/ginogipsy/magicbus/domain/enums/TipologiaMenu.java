package com.ginogipsy.magicbus.domain.enums;

import java.util.Arrays;

public enum TipologiaMenu {

    UNDEFINED                   ( "UNDEFINED"),
    FRITTO                      ( "FRITTO"),
    BEVANDA                     ( "BEVANDA"),
    PIZZA                       ( "PIZZA"),
    CALZONE                     ( "CALZONE"),
    PIZZA_FRITTA                ( "PIZZA_FRITTA");


    private final String descrizione;

    TipologiaMenu(final String descrizione){
        this.descrizione = descrizione;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public static TipologiaMenu getTipologiaMenu(final String descrizione){
        return Arrays.stream(TipologiaMenu.values()).filter(v -> v.getDescrizione().equals(descrizione)).findAny().orElse(UNDEFINED);
    }

    @Override
    public String toString() {
        return "TipologiaMenu{" +
                "descrizione='" + descrizione + '\'' +
                '}';
    }
}

