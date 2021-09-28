package com.ginogipsy.magicbus.dto;


import lombok.Data;

@Data
public class OrdineBibitaDTO {

    private Integer id;
    private OrdineDTO ordine;
    private BibitaDTO bibita;
    private Integer quantita;
    private Boolean annullato;
    private Double costoTotale;
}
