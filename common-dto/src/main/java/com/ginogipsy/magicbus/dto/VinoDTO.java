package com.ginogipsy.magicbus.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
public class VinoDTO {

    private Integer id;
    private String qualitaVino;
    private String nome;
    private String descrizione;
    private Double gradoAlcolico;
    private Double costo;
    private CantinaDTO cantina;
    private Set<UserDTO> users;
    private Boolean disponibile;
}
