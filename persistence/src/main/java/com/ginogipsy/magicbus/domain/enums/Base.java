package com.ginogipsy.magicbus.domain.enums;

import java.util.Arrays;

public enum Base {

    UNDEFINED         ( "UNDEFINED"),
    BIANCA            ( "BIANCA"),
    ROSSA             ( "ROSSA"),
    ALTRO             ( "ALTRO");


    private final String descrizione;

    Base(final String descrizione){
        this.descrizione = descrizione;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public static Base getBase(final String descrizione){
        return Arrays.stream(Base.values()).filter(v -> v.getDescrizione().equals(descrizione)).findAny().orElse(UNDEFINED);
    }

    @Override
    public String toString() {
        return "Base{" +
                "descrizione='" + descrizione + '\'' +
                '}';
    }
}

