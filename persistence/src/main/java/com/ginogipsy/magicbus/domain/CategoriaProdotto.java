package com.ginogipsy.magicbus.domain;

import java.util.Arrays;

public enum CategoriaProdotto {

    UNDEFINED         (0, "UNDEFINED"),
    PIZZA             (1, "PIZZA"),
    CALZONE           (2, "CALZONE"),
    PIZZA_FRITTA      (3, "PIZZA_FRITTA"),
    PANUOZZO          (4, "PANUOZZO"),
    PANINO            (5, "PANINO"),
    FRITTO            (6, "FRITTO"),
    BIBITA            (7, "BIBITA");

    private final int id;
    private final String descrizione;

    CategoriaProdotto(final int id, final String descrizione){
        this.id = id;
        this.descrizione = descrizione;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public int getId() {
        return id;
    }

    public static CategoriaProdotto getCategoriaProdotto(final int id){
        return Arrays.stream(CategoriaProdotto.values()).filter(v -> v.getId() == id).findAny().orElse(UNDEFINED);
    }

    public static CategoriaProdotto getCategoriaProdotto(final String descrizione){
        return Arrays.stream(CategoriaProdotto.values()).filter(v -> v.getDescrizione().equals(descrizione)).findAny().orElse(UNDEFINED);
    }
}
