package com.ginogipsy.magicbus.dto;

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
