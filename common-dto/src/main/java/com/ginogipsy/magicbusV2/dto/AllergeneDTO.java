package com.ginogipsy.magicbusV2.dto;

import lombok.Data;

import java.util.Set;

@Data
public class AllergeneDTO {

    private Integer id;
    private String nome;
    private String descrizione;
    private Set<IngredienteDTO> ingredienti;
}
