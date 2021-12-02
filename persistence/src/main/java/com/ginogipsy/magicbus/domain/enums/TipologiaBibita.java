package com.ginogipsy.magicbus.domain.enums;

import java.util.Arrays;

public enum TipologiaBibita {

    UNDEFINED                   ( "UNDEFINED"),
    ACQUA                       ( "ACQUA"),
    COLA                        ( "COLA"),
    BIRRA                       ( "BIRRA"),
    VINO                        ( "VINO"),
    ANALCOLICO                  ( "ANALCOLICO"),
    AMARO                       ( "AMARO"),
    GRAPPA                      ( "GRAPPA"),
    ARANCIATA                   ( "ARANCIATA"),
    CHINOTTO                    ( "CHINOTTO"),
    CAFFE                       ( "CAFFE"),
    SUPERALCOLICO               ( "SUPERALCOLICO");

    private final String descrizione;

    TipologiaBibita(final String descrizione){
        this.descrizione = descrizione;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public static TipologiaBibita getTipologiaBibite(final String descrizione){
        return Arrays.stream(TipologiaBibita.values()).filter(v -> v.getDescrizione().equals(descrizione)).findAny().orElse(UNDEFINED);
    }

    @Override
    public String toString() {
        return "TipologiaBibite{" +
                "descrizione='" + descrizione + '\'' +
                '}';
    }
}
