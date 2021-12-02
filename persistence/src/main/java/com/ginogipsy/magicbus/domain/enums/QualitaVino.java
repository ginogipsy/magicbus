package com.ginogipsy.magicbus.domain.enums;

import java.util.Arrays;

public enum QualitaVino {

    UNDEFINED         ( "UNDEFINED"),
    PINOT_GRIGIO      ( "PINOT_GRIGIO"),
    PECORINO          ( "PECORINO"),
    FALANGHINA        ( "FALANGHINA"),
    CABERNET          ( "CABERNET"),
    PINOT_NERO        ( "PINOT_NERO");

    private final String descrizione;

    QualitaVino(final String descrizione){

        this.descrizione = descrizione;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public static QualitaVino getQualitaVino(final String descrizione){
        return Arrays.stream(QualitaVino.values()).filter(v -> v.getDescrizione().equals(descrizione)).findAny().orElse(UNDEFINED);
    }

    @Override
    public String toString() {
        return "QualitaVino{" +
                "descrizione='" + descrizione + '\'' +
                '}';
    }
}