package com.ginogipsy.magicbusV2.dto;


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
    private String tipologiaBirra;
    private BirrificioDTO birrificio;
    private Set<UserDTO> users;
    private Boolean disponibile;
}
