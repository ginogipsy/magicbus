package com.ginogipsy.magicbusV2.dto;

import com.ginogipsy.magicbusV2.domain.Status;
import com.ginogipsy.magicbusV2.domain.TipologiaBibite;
import com.ginogipsy.magicbusV2.domain.TipologiaMenu;
import com.ginogipsy.magicbusV2.domain.UnitaDiMisura;
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
