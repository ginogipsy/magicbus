package com.ginogipsy.magicbus.dto;

import lombok.Data;

import java.util.Set;

@Data
public class FornitoreDTO {

    private Integer id;
    private String nome;
    private String via;
    private Integer civico;
    private String cap;
    private String citta;
    private String partitaIva;
    private Set<MarcaProdottoDTO> marche;
}
