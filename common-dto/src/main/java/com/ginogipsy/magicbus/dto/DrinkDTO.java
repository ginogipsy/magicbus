package com.ginogipsy.magicbus.dto;


import com.ginogipsy.magicbus.domain.enums.StatusEnum;
import com.ginogipsy.magicbus.domain.enums.DrinkTypeEnum;
import com.ginogipsy.magicbus.domain.enums.MeasureUnitEnum;
import lombok.Data;

/**
 * @author ginogipsy
 */

@Data
public class DrinkDTO {

    private Integer id;
    private String name;
    private DrinkTypeEnum drinkTypeEnum;
    private String description;
    private Double literCost;
    private Double size;
    private MeasureUnitEnum measureUnitEnum;
    private Double cost;
    private StatusEnum statusEnum;
}
