package com.ginogipsy.magicbus.dto;

import com.ginogipsy.magicbus.domain.Status;
import com.ginogipsy.magicbus.domain.TipologiaBibite;
import com.ginogipsy.magicbus.domain.TipologiaMenu;
import com.ginogipsy.magicbus.domain.UnitaDiMisura;
import lombok.Data;

@Data
public class BibitaDTO {

    private Integer id;
    private String nome;
    private TipologiaBibite tipologia;
    private String descrizione;
    private Double costoAlLitro;
    private Double formato;
    private UnitaDiMisura unitaDiMisura;
    private Double costo;
    private Status status;
    private TipologiaMenu tipologiaMenu;
}
