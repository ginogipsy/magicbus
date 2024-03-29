package com.ginogipsy.magicbus.dto;

import lombok.Data;

import java.util.Set;

/**
 * @author ginogipsy
 */

@Data
public class AvailableTimeDTO {

    private Integer id;
    private Integer hour;
    private Integer minutes;
    private Set<OrderDTO> orders;
}
