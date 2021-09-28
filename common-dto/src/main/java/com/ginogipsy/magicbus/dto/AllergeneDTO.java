package com.ginogipsy.magicbus.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Set;

@Data
public class AllergeneDTO {



    private Integer id;
    @NotBlank
    private String nome;
    private String descrizione;
    private Set<IngredienteDTO> ingredienti;
}
