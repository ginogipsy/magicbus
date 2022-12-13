package com.ginogipsy.magicbus.dto;

import lombok.Data;

/**
 * @author ginogipsy
 */

@Data
public class WineOrderDTO {

    private Integer id;
    private OrderDTO ordine;
    private WineDTO vino;
    private Integer quantita;
    private Boolean annullato;
    private Double costoTotale;

}
