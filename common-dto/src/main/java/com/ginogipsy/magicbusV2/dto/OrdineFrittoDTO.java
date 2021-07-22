package com.ginogipsy.magicbusV2.dto;


import lombok.Data;

@Data
public class OrdineFrittoDTO {

    private Integer id;
    private OrdineDTO ordine;
    private FrittoDTO fritto;
    private Integer quantita;
    private Boolean annullato;
    private Double costoTotale;

}
