package com.ginogipsy.magicbus.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Set;

/**
 * @author ginogipsy
 */

@Data
public class AllergenDTO {



    private Integer id;
    @NotBlank
    private String name;
    private String description;
    private Set<IngredientDTO> ingredients;
}
