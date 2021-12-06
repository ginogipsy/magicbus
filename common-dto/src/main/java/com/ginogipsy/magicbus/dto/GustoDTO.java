package com.ginogipsy.magicbus.dto;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ginogipsy.magicbus.domain.enums.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@NoArgsConstructor
public class GustoDTO {

    @NotNull
    @NonNull
    @NotEmpty
    private String nome;

    @NotNull
    @NonNull
    @NotEmpty
    private String descrizioneGusto;
    private Boolean tradizionale;
    private Status status;
    private Double costo;
    private Boolean versioneVeganaDisponibile;

    @JsonIgnore
    private Byte[] immagine;
    private TipologiaMenu tipologiaMenu;
    private Base base;
    private PeriodoDisponibilita periodoDisponibilita;
    private CategoriaProdotto categoriaProdotto;
    private Set<UserDTO> users;
    private boolean disponibile;
    private boolean gustoUtente;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private UserDTO userCreator;
    private String username;









}
