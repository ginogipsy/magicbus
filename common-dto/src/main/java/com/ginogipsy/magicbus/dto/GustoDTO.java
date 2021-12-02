package com.ginogipsy.magicbus.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ginogipsy.magicbus.domain.enums.*;
import lombok.Data;

@Data
public class GustoDTO {

    private String nome;
    private String descrizioneGusto;
    private Boolean tradizionale;
    private Status status;
    private Double costo;
    private Boolean versioneVeganaDisponibile;

    @JsonIgnore
    private Byte[] immagine;
    private TipologiaMenu tipologiaMenu;
    private Base base;
    private PeriodoDisponibilita periodoDisponibilita;
    private CategoriaProdotto categoriaProdotto;



}
