package com.ginogipsy.magicbus.domain;

import java.util.Arrays;

public enum Base {

    UNDEFINED         (0, "UNDEFINED"),
    BIANCA            (1, "BIANCA"),
    ROSSA             (2, "ROSSA"),
    ALTRO             (3, "ALTRO");

    private final int id;
    private final String descrizione;

    Base(final int id, final String descrizione){
        this.id = id;
        this.descrizione = descrizione;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public int getId() {
        return id;
    }

    public static Base getBase(final int id){
        return Arrays.stream(Base.values()).filter(v -> v.getId() == id).findAny().orElse(UNDEFINED);
    }

    public static Base getBase(final String descrizione){
        return Arrays.stream(Base.values()).filter(v -> v.getDescrizione().equals(descrizione)).findAny().orElse(UNDEFINED);
    }

    @Override
    public String toString() {
        return "Base{" +
                "id=" + id +
                ", descrizione='" + descrizione + '\'' +
                '}';
    }
}

