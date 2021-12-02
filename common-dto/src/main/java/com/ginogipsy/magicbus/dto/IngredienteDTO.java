package com.ginogipsy.magicbus.dto;


import com.ginogipsy.magicbus.domain.enums.PeriodoDisponibilita;
import com.ginogipsy.magicbus.domain.enums.TipologiaIngrediente;
import com.ginogipsy.magicbus.domain.enums.UnitaDiMisura;
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
    private UnitaDiMisura unitaDiMisura;
    private Set<AllergeneDTO> allergeni;
    private TipologiaIngrediente tipologiaIngrediente;
    private PeriodoDisponibilita periodoDisponibilita;
    private MarcaProdottoDTO marcaProdottoDTO;
    private Set<GustoIngredienteDTO> ingredienteGusti;
    private Set<FrittoIngredienteDTO> ingredienteFritti;
    private Set<ImpastoIngredienteDTO> ingredienteImpasti;
}
