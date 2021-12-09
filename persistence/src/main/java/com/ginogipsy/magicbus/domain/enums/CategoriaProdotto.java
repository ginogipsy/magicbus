package com.ginogipsy.magicbus.domain.enums;

import java.util.Arrays;

public enum CategoriaProdotto {

    UNDEFINED         ("UNDEFINED"),
    PIZZA             ("PIZZA"),
    CALZONE           ("CALZONE"),
    PIZZA_FRITTA      ("PIZZA_FRITTA"),
    PANUOZZO          ("PANUOZZO"),
    PANINO            ("PANINO"),
    FRITTO            ("FRITTO"),
    BIBITA            ("BIBITA"),
    ARANCINO          ("ARANCINO"),
    FRITTO_CLASSICO   ("FRITTO CLASSICO");


    private final String descrizione;

    CategoriaProdotto(final String descrizione){
        this.descrizione = descrizione;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public static CategoriaProdotto getCategoriaProdotto(final String descrizione){
        return Arrays.stream(CategoriaProdotto.values()).filter(v -> v.getDescrizione().equals(descrizione)).findAny().orElse(UNDEFINED);
    }

    @Override
    public String toString() {
        return "CategoriaProdotto{" +
                "descrizione='" + descrizione + '\'' +
                '}';
    }
}
