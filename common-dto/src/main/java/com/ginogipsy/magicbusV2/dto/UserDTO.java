package com.ginogipsy.magicbusV2.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class UserDTO {

    private int id;
    private String email;
    private String username;

    @JsonIgnore
    private String password;
    private String nome;
    private String cognome;
    private Integer numeroCellulare;
    private String indirizzo;
    private Integer civico;
    private String citta;
    private Integer cap;
    private String codiceFiscale;

    @JsonIgnore
    private String status;
    @JsonIgnore
    private List<RoleDTO> roles;
    private Set<GustoDTO> gustiPreferiti;
    //private Set<FrittoDTO> frittiPreferiti;
    //private Set<BibitaDTO> bibitePreferite;
    //private Set<Ordine> ordini;
}
