package com.ginogipsy.magicbusV2.dto;

import lombok.Data;

import java.util.Set;

@Data
public class MarcaProdottoDTO {

    private Integer id;
    private String nome;
    private String descrizione;
    private Set<FornitoreDTO> fornitore;
    private Set<IngredienteDTO> ingredienti;
}
