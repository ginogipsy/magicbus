package com.ginogipsy.magicbus.dto;


import lombok.Data;

@Data
public class ToppingOrderDTO {

    private Integer id;
    private OrderDTO order;
    private ToppingDTO taste;
    private DoughDTO dough;
    private Integer quantity;
    private Boolean canceled;
    private Double totalCost;
}
