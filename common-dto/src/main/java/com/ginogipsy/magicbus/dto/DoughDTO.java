package com.ginogipsy.magicbus.dto;

import lombok.Data;

import java.util.List;

/**
 * @author ginogipsy
 */

@Data
public class DoughDTO {

    private Integer id;
    private String name;
    private String description;
    private Double additionalCost;
    private List<String> ingredients;
}
