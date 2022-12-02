package com.ginogipsy.magicbus.dto;

import com.ginogipsy.magicbus.domain.enums.MeasureUnitEnum;
import lombok.Data;

/**
 * @author ginogipsy
 */

@Data
public class DoughIngredientDTO {

    private Integer id;
    private IngredientDTO ingredient;
    private DoughDTO dough;
    private Double quantity;
    private MeasureUnitEnum measureUnitEnum;
}
