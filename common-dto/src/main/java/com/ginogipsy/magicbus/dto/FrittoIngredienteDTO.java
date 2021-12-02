package com.ginogipsy.magicbus.dto;

import com.ginogipsy.magicbus.domain.enums.UnitaDiMisura;
import lombok.Data;

@Data
public class FrittoIngredienteDTO {

    private Integer id;
    private FrittoDTO frittoDTO;
    private IngredienteDTO ingredienteDTO;
    private Double quantita;
    private UnitaDiMisura unitaDiMisura;

}
