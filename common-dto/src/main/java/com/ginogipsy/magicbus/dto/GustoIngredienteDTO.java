package com.ginogipsy.magicbus.dto;

import com.ginogipsy.magicbus.domain.enums.MeasureUnit;
import lombok.Data;

@Data
public class GustoIngredienteDTO {

    private Integer id;
    private GustoDTO gustoDTO;
    private IngredienteDTO ingredienteDTO;
    private Double quantita;
    private MeasureUnit measureUnit;
}
