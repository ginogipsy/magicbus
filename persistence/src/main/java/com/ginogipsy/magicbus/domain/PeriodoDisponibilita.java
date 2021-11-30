package com.ginogipsy.magicbus.domain;

import java.util.Arrays;

public enum PeriodoDisponibilita {

    UNDEFINED         (0, "UNDEFINED"),
    INVERNALE         (1, "INVERNALE"),
    AUTUNNALE         (2, "AUTUNNALE"),
    ESTIVO            (3, "ESTIVO"),
    PRIMAVERILE       (4, "PRIMAVERILE"),
    SPECIALE          (5, "SPECIALE"),
    SEMPRE            (6, "SEMPRE");

    private final int id;
    private final String descrizione;

    PeriodoDisponibilita(final int id, final String descrizione){
        this.id = id;
        this.descrizione = descrizione;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public int getId() {
        return id;
    }

    public static PeriodoDisponibilita getPeriodoDisponibilita(final int id){
        return Arrays.stream(PeriodoDisponibilita.values()).filter(v -> v.getId() == id).findAny().orElse(UNDEFINED);
    }

    public static PeriodoDisponibilita getPeriodoDisponibilita(final String descrizione){
        return Arrays.stream(PeriodoDisponibilita.values()).filter(v -> v.getDescrizione().equals(descrizione)).findAny().orElse(UNDEFINED);
    }
}
