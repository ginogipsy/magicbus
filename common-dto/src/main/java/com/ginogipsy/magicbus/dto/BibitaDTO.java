package com.ginogipsy.magicbus.dto;


import com.ginogipsy.magicbus.domain.enums.Status;
import com.ginogipsy.magicbus.domain.enums.DrinkType;
import com.ginogipsy.magicbus.domain.enums.MeasureUnit;
import lombok.Data;

@Data
public class BibitaDTO {

    private Integer id;
    private String nome;
    private DrinkType tipologia;
    private String descrizione;
    private Double costoAlLitro;
    private Double formato;
    private MeasureUnit measureUnit;
    private Double costo;
    private Status status;
}
