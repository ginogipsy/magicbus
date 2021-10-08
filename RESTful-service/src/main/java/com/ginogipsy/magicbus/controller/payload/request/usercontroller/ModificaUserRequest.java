package com.ginogipsy.magicbus.controller.payload.request.usercontroller;

import lombok.Data;

@Data
public class ModificaUserRequest {

    private String email;
    private String username;
    private String nome;
    private String cognome;
    private Long numeroCellulare;
    private String indirizzo;
    private String civico;
    private String citta;
    private String cap;
    private String codiceFiscale;
}
