package com.ginogipsy.magicbusV2.dto;

import com.ginogipsy.magicbusV2.domain.Status;
import lombok.Data;

@Data
public class GustoUtenteDTO {

    private Integer id;
    private Boolean inseritaDaUtente;
    private Status status;
    private String username;
    private GustoDTO gustoDTO;
}
