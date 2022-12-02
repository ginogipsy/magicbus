package com.ginogipsy.magicbus.dto;


import lombok.Data;

/**
 * @author ginogipsy
 */

@Data
public class DrinkOrderDTO {

    private Integer id;
    private OrderDTO order;
    private DrinkDTO drink;
    private Integer quantity;
    private Boolean canceled;
    private Double totalCost;
}
