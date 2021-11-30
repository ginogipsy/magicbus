package com.ginogipsy.magicbus.domain;


import java.util.Arrays;

public enum UnitaDiMisura {

    UNDEFINED         (0, "UNDEFINED"),
    KG                (1, "KG"),
    G                 (2, "G"),
    LT                (3, "LT"),
    CL                (4, "CL"),
    ML                (5, "ML"),
    PEZZI             (6, "PEZZI"),
    GOCCE             (7, "GOCCE"),
    CUCCHIAI          (8, "CUCCHIAI"),
    CUCCHIAINI        (9, "CUCCHIAINI"),
    TAZZE             (10, "TAZZE");

    private final int id;
    private final String descrizione;

    UnitaDiMisura(final int id, final String descrizione) {
        this.id = id;
        this.descrizione = descrizione;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public int getId() {
        return id;
    }

    public static UnitaDiMisura getUnitaDiMisura(final int id){
        return Arrays.stream(UnitaDiMisura.values()).filter(v -> v.getId() == id).findAny().orElse(UNDEFINED);
    }

    public static UnitaDiMisura getUnitaDiMisura(final String descrizione){
        return Arrays.stream(UnitaDiMisura.values()).filter(v -> v.getDescrizione().equals(descrizione)).findAny().orElse(UNDEFINED);
    }

    @Override
    public String toString() {
        return "UnitaDiMisura{" +
                "id=" + id +
                ", descrizione='" + descrizione + '\'' +
                '}';
    }
}
