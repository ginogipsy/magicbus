package com.ginogipsy.magicbusV2.dto;

import lombok.Data;
import org.joda.time.LocalDate;

import java.util.List;

@Data
public class OrdineDTO {

    private Integer id;
    private LocalDate oraInserimento;
    private LocalDate oraApprovazione;
    private OrarioDisponibileDTO orarioDisponibile;
    private UserDTO user;
    private List<OrdineFrittoDTO> ordineFritti;
    private List<OrdineGustoDTO> ordineGusti;
    private List<OrdineBibitaDTO> ordineBibite;
    private List<OrdineBirraDTO> ordineBirre;
    private List<OrdineVinoDTO> ordineVini;
    private OrdinePagamentoDTO ordinePagamento;

}
