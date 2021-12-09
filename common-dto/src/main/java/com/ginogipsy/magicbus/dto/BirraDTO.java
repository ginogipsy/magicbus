package com.ginogipsy.magicbus.dto;


import com.ginogipsy.magicbus.domain.enums.BeerType;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
public class BirraDTO {

    private Integer id;
    private String nome;
    private String descrizione;
    private Double gradoAlcolico;
    private Double costo;
    private BeerType beerType;
    private BirrificioDTO birrificio;
    private Set<UserDTO> users;
    private Boolean disponibile;
}
