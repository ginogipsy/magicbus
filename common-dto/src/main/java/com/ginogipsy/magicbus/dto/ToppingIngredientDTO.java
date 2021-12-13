package com.ginogipsy.magicbus.dto;

import com.ginogipsy.magicbus.domain.enums.MeasureUnit;
import lombok.Data;

@Data
public class ToppingIngredientDTO {

    private Integer id;
    private ToppingDTO taste;
    private IngredientDTO ingredient;
    private Double quantity;
    private MeasureUnit measureUnit;
}
