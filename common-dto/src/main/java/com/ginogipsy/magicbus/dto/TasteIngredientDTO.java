package com.ginogipsy.magicbus.dto;

import com.ginogipsy.magicbus.domain.enums.MeasureUnit;
import lombok.Data;

@Data
public class TasteIngredientDTO {

    private Integer id;
    private TasteDTO taste;
    private IngredientDTO ingredient;
    private Double quantity;
    private MeasureUnit measureUnit;
}
