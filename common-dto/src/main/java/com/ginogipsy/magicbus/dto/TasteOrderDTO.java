package com.ginogipsy.magicbus.dto;


import lombok.Data;

@Data
public class TasteOrderDTO {

    private Integer id;
    private OrderDTO order;
    private TasteDTO taste;
    private DoughDTO dough;
    private Integer quantity;
    private Boolean canceled;
    private Double totalCost;
}
