package com.ginogipsy.magicbus.domain.enums;

import java.util.Arrays;

public enum Profilo {

    GUEST          ("GUEST"),
    USER           ("USER"),
    EDITOR         ("EDITOR"),
    ADMIN          ("ADMIN"),
    MEZZ           ("MEZZ");

    private final String descrizione;

    Profilo(final String descrizione){
        this.descrizione = descrizione;
    }

    public String getProfilo() {
        return descrizione;
    }

    public static Profilo getProfilo(final String descrizione){
        return Arrays.stream(Profilo.values()).filter(v -> v.getProfilo().equals(descrizione)).findAny().orElse(GUEST);
    }
}
