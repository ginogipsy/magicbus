package com.ginogipsy.magicbus.dto;


import com.ginogipsy.magicbus.domain.enums.BeerTypeEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
 * @author ginogipsy
 */

@Data
@NoArgsConstructor
public class BeerDTO {

    private Integer id;
    private String name;
    private String description;
    private Double alcoholContent;
    private Double cost;
    private BeerTypeEnum beerTypeEnum;
    private BreweryDTO brewery;
    private Set<UserDTO> users;
    private Boolean available;
}
