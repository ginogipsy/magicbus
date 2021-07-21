package com.ginogipsy.magicbusV2.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
public class UserDTO {


    private int id;

    @NonNull
    private String email;
    @NonNull
    private String username;

    @NonNull
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private String nome;
    private String cognome;

    @NonNull
    private Long numeroCellulare;
    private String indirizzo;
    private Integer civico;
    private String citta;
    private Integer cap;
    private String codiceFiscale;

    //@JsonIgnore
    private Boolean isEnabled;
    //@JsonIgnore
    private Set<RoleDTO> roles;
    private Set<GustoDTO> gustiPreferiti;
    //private Set<FrittoDTO> frittiPreferiti;
    //private Set<BibitaDTO> bibitePreferite;
    //private Set<Ordine> ordini;

}
