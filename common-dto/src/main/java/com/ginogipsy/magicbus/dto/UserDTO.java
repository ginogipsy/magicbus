package com.ginogipsy.magicbus.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ginogipsy.magicbus.domain.Gusto;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

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
    private String numeroCellulare;
    private String indirizzo;
    private String civico;
    private String citta;
    private String cap;
    private String codiceFiscale;

    //@JsonIgnore
    private Boolean isEnabled;
    @JsonIgnore
    private Set<RoleDTO> roles;
    private Set<GustoDTO> gustiPreferiti;
    private Set<FrittoDTO> frittiPreferiti;
    private Set<VinoDTO> viniPreferiti;
    private Set<BirraDTO> birrePreferite;
    private Set<OrdineDTO> ordini;
    private Set<Gusto> gusti;



}
