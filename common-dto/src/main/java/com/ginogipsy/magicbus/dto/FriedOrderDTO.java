package com.ginogipsy.magicbus.dto;


import lombok.Data;

/**
 * @author ginogipsy
 */

@Data
public class FriedOrderDTO {

    private Integer id;
    private OrderDTO order;
    private FriedDTO fried;
    private Integer quantity;
    private Boolean canceled;
    private Double totalCost;

}
