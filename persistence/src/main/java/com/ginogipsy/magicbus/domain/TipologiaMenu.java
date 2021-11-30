package com.ginogipsy.magicbus.domain;

import java.util.Arrays;

public enum TipologiaMenu {

    UNDEFINED                   (0, "UNDEFINED"),
    FRITTO                      (1, "FRITTO"),
    BEVANDA                     (2, "BEVANDA"),
    PIZZA                       (3, "PIZZA"),
    CALZONE                     (4, "CALZONE"),
    PIZZA_FRITTA                (5, "PIZZA_FRITTA");

    private final int id;
    private final String descrizione;

    TipologiaMenu(final int id, final String descrizione){
        this.id = id;
        this.descrizione = descrizione;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public int getId() {
        return id;
    }

    public static TipologiaMenu getTipologiaMenu(final int id){
        return Arrays.stream(TipologiaMenu.values()).filter(v -> v.getId() == id).findAny().orElse(UNDEFINED);
    }

    public static TipologiaMenu getTipologiaMenu(final String descrizione){
        return Arrays.stream(TipologiaMenu.values()).filter(v -> v.getDescrizione().equals(descrizione)).findAny().orElse(UNDEFINED);
    }
}

