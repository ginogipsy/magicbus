package com.ginogipsy.magicbus.dto;


import com.ginogipsy.magicbus.domain.enums.AvailabilityPeriod;
import com.ginogipsy.magicbus.domain.enums.IngredientType;
import com.ginogipsy.magicbus.domain.enums.MeasureUnit;
import lombok.Data;

import java.util.Set;

@Data
public class IngredientDTO {

    private Integer id;
    private String name;
    private String description;
    private Boolean available;
    private Double additionalCostForClient;
    private Double purchaseCost;
    private MeasureUnit measureUnit;
    private Set<AllergenDTO> allergens;
    private IngredientType ingredientType;
    private AvailabilityPeriod availabilityPeriod;
    private BrandDTO brand;
    private Set<ToppingIngredientDTO> tastesIngredients;
    private Set<FriedIngredientDTO> friedIngredients;
    private Set<DoughIngredientDTO> doughIngredients;
}
