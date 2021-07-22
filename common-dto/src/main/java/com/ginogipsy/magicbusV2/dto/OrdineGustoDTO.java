package com.ginogipsy.magicbusV2.dto;


import lombok.Data;

@Data
public class OrdineGustoDTO {

    private Integer id;
    private OrdineDTO ordine;
    private GustoDTO gusto;
    private ImpastoDTO impasto;
    private Integer quantita;
    private Boolean annullato;
    private Double costoTotale;
}
