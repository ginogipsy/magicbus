package com.ginogipsy.magicbusV2.dto;

import com.ginogipsy.magicbusV2.domain.UnitaDiMisura;
import lombok.Data;

@Data
public class ImpastoIngredienteDTO {

    private Integer id;
    private IngredienteDTO ingredienteDTO;
    private ImpastoDTO impastoDTO;
    private Double quantita;
    private UnitaDiMisura unitaDiMisura;
}
