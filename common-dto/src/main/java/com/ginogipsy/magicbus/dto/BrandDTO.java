package com.ginogipsy.magicbus.dto;

import lombok.Data;

import java.util.List;

@Data
public class BrandDTO {

    private Integer id;
    private String name;
    private String description;
    private List<String> suppliers;
    private List<String> ingredients;
}
