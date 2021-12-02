package com.ginogipsy.magicbus.domain.enums;


import java.util.Arrays;

public enum UnitaDiMisura {

    UNDEFINED         ( "UNDEFINED"),
    KG                ( "KG"),
    G                 ( "G"),
    LT                ( "LT"),
    CL                ( "CL"),
    ML                ( "ML"),
    PEZZI             ( "PEZZI"),
    GOCCE             ( "GOCCE"),
    CUCCHIAI          ( "CUCCHIAI"),
    CUCCHIAINI        ( "CUCCHIAINI"),
    TAZZE             ( "TAZZE");


    private final String descrizione;

    UnitaDiMisura(final String descrizione) {
        this.descrizione = descrizione;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public static UnitaDiMisura getUnitaDiMisura(final String descrizione){
        return Arrays.stream(UnitaDiMisura.values()).filter(v -> v.getDescrizione().equals(descrizione)).findAny().orElse(UNDEFINED);
    }

}
