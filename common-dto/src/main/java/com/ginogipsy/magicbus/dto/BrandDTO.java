package com.ginogipsy.magicbus.dto;

import lombok.Data;

import java.util.Set;

@Data
public class BrandDTO {

    private Integer id;
    private String name;
    private String description;
    private Set<SupplierDTO> suppliers;
    private Set<IngredientDTO> ingredients;
}
