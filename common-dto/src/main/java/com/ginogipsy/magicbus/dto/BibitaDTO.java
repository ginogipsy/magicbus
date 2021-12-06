package com.ginogipsy.magicbus.dto;


import com.ginogipsy.magicbus.domain.enums.Status;
import com.ginogipsy.magicbus.domain.enums.TipologiaBibita;
import com.ginogipsy.magicbus.domain.enums.UnitaDiMisura;
import lombok.Data;

@Data
public class BibitaDTO {

    private Integer id;
    private String nome;
    private TipologiaBibita tipologia;
    private String descrizione;
    private Double costoAlLitro;
    private Double formato;
    private UnitaDiMisura unitaDiMisura;
    private Double costo;
    private Status status;
}
