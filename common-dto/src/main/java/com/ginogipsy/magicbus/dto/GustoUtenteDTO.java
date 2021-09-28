package com.ginogipsy.magicbus.dto;

import com.ginogipsy.magicbus.domain.Status;
import lombok.Data;

@Data
public class GustoUtenteDTO {

    private Integer id;
    private Boolean inseritaDaUtente;
    private Status status;
    private String username;
    private GustoDTO gustoDTO;
}
