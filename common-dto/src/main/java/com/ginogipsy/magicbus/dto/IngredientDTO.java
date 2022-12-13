package com.ginogipsy.magicbus.dto;


import com.ginogipsy.magicbus.domain.enums.AvailabilityPeriodEnum;
import com.ginogipsy.magicbus.domain.enums.IngredientTypeEnum;
import com.ginogipsy.magicbus.domain.enums.MeasureUnitEnum;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Set;

/**
 * @author ginogipsy
 */

@Data
public class IngredientDTO {

    private Integer id;
    @NotBlank(message = "Name is necessary!")
    private String name;
    @NotBlank(message = "Name is necessary!")
    private String description;
    private Boolean available;
    private Double additionalCostForClient;
    private Double purchaseCost;
    private MeasureUnitEnum measureUnitEnum;
    private Set<AllergenDTO> allergens;
    private IngredientTypeEnum ingredientTypeEnum;
    private AvailabilityPeriodEnum availabilityPeriodEnum;
    private BrandDTO brand;
    private List<String> toppings;
    private List<String> fried;
    private List<String> doughs;
}
