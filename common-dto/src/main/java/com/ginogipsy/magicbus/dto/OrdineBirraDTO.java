package com.ginogipsy.magicbus.dto;


import lombok.Data;

@Data
public class OrdineBirraDTO {

    private Integer id;
    private OrdineDTO ordine;
    private BirraDTO birra;
    private Integer quantita;
    private Boolean annullato;
    private Double costoTotale;
}
