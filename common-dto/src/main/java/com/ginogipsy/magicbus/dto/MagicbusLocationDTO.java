package com.ginogipsy.magicbus.dto;

import lombok.Data;

/**
 * @author ginogipsy
 */

@Data
public class MagicbusLocationDTO {

    private Integer id;
    private String name;
    private String description;
    private String street;
    private String number;
    private String postalCode;
    private String city;
    private String province;
    private String country;

}
