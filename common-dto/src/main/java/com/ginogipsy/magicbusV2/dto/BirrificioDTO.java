package com.ginogipsy.magicbusV2.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
public class BirrificioDTO {

    private Integer id;
    private String nome;
    private String descrizione;
    private String ubicazione;
    private String nazione;
    private Set<BirraDTO> birreDTO;
}
