package com.ginogipsy.magicbus.dto;

import lombok.Data;

import java.util.Set;

/**
 * @author ginogipsy
 */

@Data
public class SupplierDTO {

    private Integer id;
    private String name;
    private String street;
    private Integer number;
    private String postalCode;
    private String city;
    private String vatCode;
    private Set<BrandDTO> brands;
}
