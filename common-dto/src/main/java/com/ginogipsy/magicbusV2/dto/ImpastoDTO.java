package com.ginogipsy.magicbusV2.dto;

import lombok.Data;

import java.util.Set;

@Data
public class ImpastoDTO {

    private Integer id;
    private String nome;
    private String descrizione;
    private Double costoAggiuntivo;
    private Set<ImpastoIngredienteDTO> impastoIngredienti;
}
