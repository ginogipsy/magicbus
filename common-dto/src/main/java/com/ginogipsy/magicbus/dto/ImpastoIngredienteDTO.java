package com.ginogipsy.magicbus.dto;

import com.ginogipsy.magicbus.domain.enums.MeasureUnit;
import lombok.Data;

@Data
public class ImpastoIngredienteDTO {

    private Integer id;
    private IngredienteDTO ingredienteDTO;
    private ImpastoDTO impastoDTO;
    private Double quantita;
    private MeasureUnit measureUnit;
}
