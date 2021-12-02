package com.ginogipsy.magicbus.domain.enums;

import java.util.Arrays;

public enum TipologiaBirra {

    UNDEFINED                   ( "UNDEFINED"),
    PILSNER                     ( "PILSNER"),
    WEISSE                      ( "WEISSE"),
    ROSSA                       ( "ROSSA"),
    IPA                         ( "IPA"),
    APA                         ( "APA"),
    DOPPIO_MALTO                ( "DOPPIO_MALTO"),
    STOUT                       ( "STOUT");

    private final String descrizione;

    TipologiaBirra(final String descrizione){

        this.descrizione = descrizione;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public static TipologiaBirra getTipologiaBirra(final String descrizione){
        return Arrays.stream(TipologiaBirra.values()).filter(v -> v.getDescrizione().equals(descrizione)).findAny().orElse(UNDEFINED);
    }

    @Override
    public String toString() {
        return "TipologiaBirra{" +
                "descrizione='" + descrizione + '\'' +
                '}';
    }
}
