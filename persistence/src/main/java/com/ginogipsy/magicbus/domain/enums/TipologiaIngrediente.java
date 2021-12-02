package com.ginogipsy.magicbus.domain.enums;

import java.util.Arrays;

public enum TipologiaIngrediente {

    UNDEFINED                   ( "UNDEFINED"),
    CARNE                       ( "CARNE"),
    PESCE                       ( "PESCE"),
    FORMAGGIO                   ( "FORMAGGIO"),
    UOVA                        ( "UOVA"),
    SPEZIA                      ( "SPEZIA"),
    VEGETALE                    ( "VEGETALE"),
    FRUTTA                      ( "FRUTTA"),
    SALSA                       ( "SALSA"),
    MARMELLATA                  ( "MARMELLATA"),
    CONFETTURA                  ( "CONFETTURA"),
    CONFIT                      ( "CONFIT"),
    FORMAGGIO_VEGANO            ( "FORMAGGIO_VEGANO"),
    CARNE_VEGANA                ( "CARNE_VEGANA");


    private final String descrizione;

    TipologiaIngrediente(final String descrizione){

        this.descrizione = descrizione;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public static TipologiaIngrediente getTipologiaIngrediente(final String descrizione){
        return Arrays.stream(TipologiaIngrediente.values()).filter(v -> v.getDescrizione().equals(descrizione)).findAny().orElse(UNDEFINED);
    }

    @Override
    public String toString() {
        return "TipologiaIngrediente{" +
                "descrizione='" + descrizione + '\'' +
                '}';
    }
}
