package com.ginogipsy.magicbus.domain;

import java.util.Arrays;

public enum TipologiaBirra {

    UNDEFINED                   (0, "UNDEFINED"),
    PILSNER                     (1, "PILSNER"),
    WEISSE                      (2, "WEISSE"),
    ROSSA                       (3, "ROSSA"),
    IPA                         (4, "IPA"),
    APA                         (5, "APA"),
    DOPPIO_MALTO                (6, "DOPPIO_MALTO"),
    STOUT                       (7, "STOUT");

    private final int id;
    private final String descrizione;

    TipologiaBirra(final int id, final String descrizione){
        this.id = id;
        this.descrizione = descrizione;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public int getId() {
        return id;
    }

    public static TipologiaBirra getTipologiaBirra(final int id){
        return Arrays.stream(TipologiaBirra.values()).filter(v -> v.getId() == id).findAny().orElse(UNDEFINED);
    }

    public static TipologiaBirra getTipologiaBirra(final String descrizione){
        return Arrays.stream(TipologiaBirra.values()).filter(v -> v.getDescrizione().equals(descrizione)).findAny().orElse(UNDEFINED);
    }
}
