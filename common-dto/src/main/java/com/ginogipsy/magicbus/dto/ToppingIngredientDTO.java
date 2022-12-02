package com.ginogipsy.magicbus.dto;

import com.ginogipsy.magicbus.domain.enums.MeasureUnitEnum;
import lombok.Data;

@Data
public class ToppingIngredientDTO {

    private Integer id;
    private ToppingDTO topping;
    private IngredientDTO ingredient;
    private Double quantity;
    private MeasureUnitEnum measureUnitEnum;
}
