package com.ginogipsy.magicbus.domain;

import java.util.Arrays;

public enum TipologiaIngrediente {

    UNDEFINED                   (0, "UNDEFINED"),
    CARNE                       (1, "CARNE"),
    PESCE                       (2, "PESCE"),
    FORMAGGIO                   (3, "FORMAGGIO"),
    UOVA                        (4, "UOVA"),
    SPEZIA                      (5, "SPEZIA"),
    VEGETALE                    (6, "VEGETALE"),
    FRUTTA                      (7, "FRUTTA"),
    SALSA                       (8, "SALSA"),
    MARMELLATA                  (9, "MARMELLATA"),
    CONFETTURA                  (10, "CONFETTURA"),
    CONFIT                      (11, "CONFIT"),
    FORMAGGIO_VEGANO            (12, "FORMAGGIO_VEGANO"),
    CARNE_VEGANA                (13, "CARNE_VEGANA");

    private final int id;
    private final String descrizione;

    TipologiaIngrediente(final int id, final String descrizione){
        this.id = id;
        this.descrizione = descrizione;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public int getId() {
        return id;
    }

    public static TipologiaIngrediente getTipologiaIngrediente(final int id){
        return Arrays.stream(TipologiaIngrediente.values()).filter(v -> v.getId() == id).findAny().orElse(UNDEFINED);
    }

    public static TipologiaIngrediente getTipologiaIngrediente(final String descrizione){
        return Arrays.stream(TipologiaIngrediente.values()).filter(v -> v.getDescrizione().equals(descrizione)).findAny().orElse(UNDEFINED);
    }
}
