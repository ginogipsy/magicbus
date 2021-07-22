package com.ginogipsy.magicbusV2.dto;

import com.ginogipsy.magicbusV2.domain.UnitaDiMisura;
import lombok.Data;

@Data
public class GustoIngredienteDTO {

    private Integer id;
    private GustoDTO gustoDTO;
    private IngredienteDTO ingredienteDTO;
    private Double quantita;
    private UnitaDiMisura unitaDiMisura;
}
