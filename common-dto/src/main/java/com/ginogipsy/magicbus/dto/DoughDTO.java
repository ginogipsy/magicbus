package com.ginogipsy.magicbus.dto;

import lombok.Data;

import java.util.Set;

@Data
public class DoughDTO {

    private Integer id;
    private String name;
    private String description;
    private Double additionalCost;
    private Set<DoughIngredientDTO> ingredients;
}
