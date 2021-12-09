package com.ginogipsy.magicbus.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ginogipsy.magicbus.domain.enums.ProductCategory;
import com.ginogipsy.magicbus.domain.enums.Status;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Getter
@Setter
@RequiredArgsConstructor
public class FrittoDTO {

    private Integer id;
    @NotEmpty
    private String nome;
    @NotEmpty
    private String descrizioneFritto;
    private Double costo;
    private Status status;
    private Set<FrittoIngredienteDTO> frittoIngredienti;

    @JsonIgnore
    private Byte[] immagine;
    private ProductCategory productCategory;
    private Boolean disponibile;
    private boolean frittoUtente;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private UserDTO userCreator;
    private String username;
    private int appreciations;
}
