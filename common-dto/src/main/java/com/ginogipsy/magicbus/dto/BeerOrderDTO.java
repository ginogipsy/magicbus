package com.ginogipsy.magicbus.dto;


import lombok.Data;

/**
 * @author ginogipsy
 */

@Data
public class BeerOrderDTO {

    private Integer id;
    private OrderDTO order;
    private BeerDTO beer;
    private Integer quantity;
    private Boolean canceled;
    private Double totalCost;
}
