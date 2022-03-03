package com.ginogipsy.magicbus.controller.payload.request;

import com.ginogipsy.magicbus.domain.enums.AvailabilityPeriod;
import com.ginogipsy.magicbus.domain.enums.IngredientType;
import com.ginogipsy.magicbus.domain.enums.MeasureUnit;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class InsertIngredientRequest {

    @NotBlank(message = "Name is necessary!")
    private String name;
    @NotBlank(message = "Name is necessary!")
    private String description;
    private Boolean available;
    private Double additionalCostForClient;
    private Double purchaseCost;
    private MeasureUnit measureUnit;
    private IngredientType ingredientType;
    private AvailabilityPeriod availabilityPeriod;
    private String brandName;

}
