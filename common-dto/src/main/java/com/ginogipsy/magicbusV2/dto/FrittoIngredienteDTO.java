package com.ginogipsy.magicbusV2.dto;

import com.ginogipsy.magicbusV2.domain.UnitaDiMisura;
import lombok.Data;

@Data
public class FrittoIngredienteDTO {

    private Integer id;
    private FrittoDTO frittoDTO;
    private IngredienteDTO ingredienteDTO;
    private Double quantita;
    private UnitaDiMisura unitaDiMisura;

}
