package com.ginogipsy.magicbusV2.dto;

import com.ginogipsy.magicbusV2.domain.CategoriaProdotto;
import com.ginogipsy.magicbusV2.domain.Status;
import com.ginogipsy.magicbusV2.domain.TipologiaMenu;
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
