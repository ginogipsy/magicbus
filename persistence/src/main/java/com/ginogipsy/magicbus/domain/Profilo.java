package com.ginogipsy.magicbus.domain;

import java.util.Arrays;

public enum Profilo {

    GUEST          (0, "GUEST"),
    USER           (1, "USER"),
    EDITOR         (2, "EDITOR"),
    ADMIN          (3, "ADMIN"),
    MEZZ           (4, "MEZZ");

    private final int id;
    private final String descrizione;

    Profilo(final int id, final String descrizione){
        this.id = id;
        this.descrizione = descrizione;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public int getId() {
        return id;
    }

    public static Profilo getProfilo(final int id){
        return Arrays.stream(Profilo.values()).filter(v -> v.getId() == id).findAny().orElse(GUEST);
    }

    public static Profilo getProfilo(final String descrizione){
        return Arrays.stream(Profilo.values()).filter(v -> v.getDescrizione().equals(descrizione)).findAny().orElse(GUEST);
    }
}
