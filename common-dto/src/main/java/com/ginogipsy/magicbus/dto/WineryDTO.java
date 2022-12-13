package com.ginogipsy.magicbus.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
 * @author ginogipsy
 */

@Data
@NoArgsConstructor
public class WineryDTO {

    private Integer id;
    private String name;
    private String description;
    private String location;
    private String nationality;
    private Set<WineDTO> wines;

}
