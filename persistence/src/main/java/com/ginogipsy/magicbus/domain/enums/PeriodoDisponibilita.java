package com.ginogipsy.magicbus.domain.enums;

import java.util.Arrays;

public enum PeriodoDisponibilita {

    UNDEFINED         ( "UNDEFINED"),
    INVERNALE         ( "INVERNALE"),
    AUTUNNALE         ( "AUTUNNALE"),
    ESTIVO            ( "ESTIVO"),
    PRIMAVERILE       ( "PRIMAVERILE"),
    SPECIALE          ( "SPECIALE"),
    SEMPRE            ( "SEMPRE");


    private final String descrizione;

    PeriodoDisponibilita(final String descrizione){
        this.descrizione = descrizione;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public static PeriodoDisponibilita getPeriodoDisponibilita(final String descrizione){
        return Arrays.stream(PeriodoDisponibilita.values()).filter(v -> v.getDescrizione().equals(descrizione)).findAny().orElse(UNDEFINED);
    }

    @Override
    public String toString() {
        return "PeriodoDisponibilita{" +
                "descrizione='" + descrizione + '\'' +
                '}';
    }
}
