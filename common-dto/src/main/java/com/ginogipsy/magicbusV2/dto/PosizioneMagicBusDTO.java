package com.ginogipsy.magicbusV2.dto;

import lombok.Data;

@Data
public class PosizioneMagicBusDTO {

    private Integer id;
    private String nome;
    private String descrizione;
    private String via;
    private String civico;
    private String cap;
    private String citta;
    private String provincia;
    private String nazione;

}
