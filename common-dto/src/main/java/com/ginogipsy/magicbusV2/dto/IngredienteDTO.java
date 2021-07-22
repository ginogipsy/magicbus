package com.ginogipsy.magicbusV2.dto;

import com.ginogipsy.magicbusV2.domain.PeriodoDisponibilita;
import com.ginogipsy.magicbusV2.domain.TipologiaIngrediente;
import com.ginogipsy.magicbusV2.domain.UnitaDiMisura;
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
