package com.ginogipsy.magicbus.dto;


import com.ginogipsy.magicbus.domain.enums.Status;
import com.ginogipsy.magicbus.domain.enums.DrinkType;
import com.ginogipsy.magicbus.domain.enums.MeasureUnit;
import lombok.Data;

@Data
public class DrinkDTO {

    private Integer id;
    private String name;
    private DrinkType drinkType;
    private String description;
    private Double literCost;
    private Double size;
    private MeasureUnit measureUnit;
    private Double cost;
    private Status status;
}
