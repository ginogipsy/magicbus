package com.ginogipsy.magicbus.controller.payload.request;


import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Set;

@Data
public class SignupRequest {

    @NotBlank
    private String username;

    @NotBlank
    private String email;

    @NotBlank
    private String password;
    private String nome;
    private String cognome;

    @NotBlank
    private String numeroCellulare;
    private String indirizzo;
    private String civico;
    private String citta;
    private String cap;
    private String codiceFiscale;
    private Set<String> roles;
}
