package com.ginogipsy.magicbus.dto;

import com.ginogipsy.magicbus.domain.enums.MeasureUnit;
import lombok.Data;

@Data
public class FriedIngredientDTO {

    private Integer id;
    private FriedDTO fried;
    private IngredientDTO ingredient;
    private Double quantity;
    private MeasureUnit measureUnit;

}
