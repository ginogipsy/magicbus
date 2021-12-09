package com.ginogipsy.magicbus.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ginogipsy.magicbus.domain.enums.*;
import lombok.*;
import org.springframework.lang.NonNull;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter
@Setter
@RequiredArgsConstructor
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
    private Base base;
    private PeriodoDisponibilita periodoDisponibilita;
    private CategoriaProdotto categoriaProdotto;

    @JsonIgnore
    private Set<UserDTO> users;
    private boolean disponibile;
    private boolean gustoUtente;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private UserDTO userCreator;
    private String username;
    private Integer appreciations;









}
