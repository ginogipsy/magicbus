package com.ginogipsy.magicbus.dto;


import com.ginogipsy.magicbus.domain.enums.CategoriaProdotto;
import com.ginogipsy.magicbus.domain.enums.Status;
import com.ginogipsy.magicbus.domain.enums.TipologiaMenu;
import lombok.Data;

import java.util.Set;

@Data
public class FrittoDTO {

    private Integer id;
    private String nome;
    private String descrizione;
    private Double costo;
    private Status status;
    private Set<FrittoIngredienteDTO> frittoIngredienti;
    private Byte[] immagine;
    private TipologiaMenu tipologiaMenu;
    private CategoriaProdotto categoriaProdotto;
    private Set<UserDTO> users;
    private Boolean disponibile;
}
