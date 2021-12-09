package com.ginogipsy.magicbus.dto;


import com.ginogipsy.magicbus.domain.enums.AvailabilityPeriod;
import com.ginogipsy.magicbus.domain.enums.IngredientType;
import com.ginogipsy.magicbus.domain.enums.MeasureUnit;
import lombok.Data;

import java.util.Set;

@Data
public class IngredienteDTO {

    private Integer id;
    private String nome;
    private String descrizione;
    private Boolean disponibile;
    private Double costoAggiuntaCliente;
    private Double costoDiAcquisto;
    private MeasureUnit measureUnit;
    private Set<AllergeneDTO> allergeni;
    private IngredientType ingredientType;
    private AvailabilityPeriod availabilityPeriod;
    private MarcaProdottoDTO marcaProdottoDTO;
    private Set<GustoIngredienteDTO> ingredienteGusti;
    private Set<FrittoIngredienteDTO> ingredienteFritti;
    private Set<ImpastoIngredienteDTO> ingredienteImpasti;
}
