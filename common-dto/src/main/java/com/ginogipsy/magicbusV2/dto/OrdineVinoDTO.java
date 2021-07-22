package com.ginogipsy.magicbusV2.dto;

import lombok.Data;

@Data
public class OrdineVinoDTO {

    private Integer id;
    private OrdineDTO ordine;
    private VinoDTO vino;
    private Integer quantita;
    private Boolean annullato;
    private Double costoTotale;

}
