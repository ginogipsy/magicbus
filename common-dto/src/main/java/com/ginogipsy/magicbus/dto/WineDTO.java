package com.ginogipsy.magicbus.dto;


import com.ginogipsy.magicbus.domain.enums.WineQuality;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
public class WineDTO {

    private Integer id;
    private WineQuality wineQuality;
    private String name;
    private String description;
    private Double alcoholicContent;
    private Double cost;
    private WineryDTO winery;
    private Set<UserDTO> users;
    private Boolean available;
}
