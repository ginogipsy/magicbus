package com.ginogipsy.magicbus.domain;

import java.util.Arrays;

public enum TipologiaBibite {

    UNDEFINED                   (0, "UNDEFINED"),
    ACQUA                       (1, "ACQUA"),
    COLA                        (2, "COLA"),
    BIRRA                       (3, "BIRRA"),
    VINO                        (4, "VINO"),
    ANALCOLICO                  (5, "ANALCOLICO"),
    AMARO                       (6, "AMARO"),
    GRAPPA                      (7, "GRAPPA"),
    ARANCIATA                   (8, "ARANCIATA"),
    CHINOTTO                    (9, "CHINOTTO"),
    CAFFE                       (10, "CAFFE"),
    SUPERALCOLICO               (11, "SUPERALCOLICO");

    private final int id;
    private final String descrizione;

    TipologiaBibite(final int id, final String descrizione){
        this.id = id;
        this.descrizione = descrizione;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public int getId() {
        return id;
    }

    public static TipologiaBibite getTipologiaBibite(final int id){
        return Arrays.stream(TipologiaBibite.values()).filter(v -> v.getId() == id).findAny().orElse(UNDEFINED);
    }

    public static TipologiaBibite getTipologiaBibite(final String descrizione){
        return Arrays.stream(TipologiaBibite.values()).filter(v -> v.getDescrizione().equals(descrizione)).findAny().orElse(UNDEFINED);
    }
}
