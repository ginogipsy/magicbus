package com.ginogipsy.magicbus.dto;

import com.ginogipsy.magicbus.domain.enums.MeasureUnit;
import lombok.Data;

@Data
public class DoughIngredientDTO {

    private Integer id;
    private IngredientDTO ingredient;
    private DoughDTO dough;
    private Double quantity;
    private MeasureUnit measureUnit;
}
