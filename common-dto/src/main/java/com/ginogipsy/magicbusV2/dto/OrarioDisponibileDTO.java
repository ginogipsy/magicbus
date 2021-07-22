package com.ginogipsy.magicbusV2.dto;

import lombok.Data;

import java.util.Set;

@Data
public class OrarioDisponibileDTO {

    private Integer id;
    private Integer ora;
    private Integer minuti;
    private Set<OrdineDTO> ordini;
}
