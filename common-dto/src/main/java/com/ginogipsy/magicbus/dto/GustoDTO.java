package com.ginogipsy.magicbus.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class GustoDTO {

    private String nome;
    private String descrizioneGusto;
    private Boolean tradizionale;
    private String status;
    private Double costo;
    private Boolean versioneVeganaDisponibile;

    @JsonIgnore
    private Byte[] immagine;
    private String tipologiaMenu;
    private String base;
    private String periodoDisponibilita;
    private String categoriaProdotto;



}
