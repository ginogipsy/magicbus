package com.ginogipsy.magicbus.domain;

import java.util.Arrays;

public enum QualitaVino {

    UNDEFINED         (0, "UNDEFINED"),
    PINOT_GRIGIO      (1, "PINOT_GRIGIO"),
    PECORINO          (2, "PECORINO"),
    FALANGHINA        (3, "FALANGHINA"),
    CABERNET          (4, "CABERNET"),
    PINOT_NERO        (5, "PINOT_NERO");

    private final int id;
    private final String descrizione;

    QualitaVino(final int id, final String descrizione){
        this.id = id;
        this.descrizione = descrizione;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public int getId() {
        return id;
    }

    public static QualitaVino getQualitaVino(final int id){
        return Arrays.stream(QualitaVino.values()).filter(v -> v.getId() == id).findAny().orElse(UNDEFINED);
    }

    public static QualitaVino getQualitaVino(final String descrizione){
        return Arrays.stream(QualitaVino.values()).filter(v -> v.getDescrizione().equals(descrizione)).findAny().orElse(UNDEFINED);
    }

}