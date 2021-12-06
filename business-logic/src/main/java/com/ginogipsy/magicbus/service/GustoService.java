package com.ginogipsy.magicbus.service;

import com.ginogipsy.magicbus.dto.GustoDTO;
import com.ginogipsy.magicbus.dto.UserDTO;

import java.util.List;

public interface GustoService {

    List<GustoDTO> findGustiByStatus(String status);
    GustoDTO findGustoByNome(String nome);
    List<GustoDTO> findGustiNameContains(String nome);
    List<GustoDTO> findByBase(String base);
    List<GustoDTO> findByCategoriaProdotto(String categoriaProdotto);
    List<GustoDTO> findByPeriodoDisponibilita(String periodoDisponibilita);
    List<GustoDTO> findByDisponibilita(boolean disponibilita, String status);
    List<GustoDTO> findByDisponibilitaAndPeriodoDisponibilita(boolean disponibile, String periodoDisponibilita);
    List<GustoDTO> findByInseritaDaUtente(boolean inseritaDaUtente);
    List<GustoDTO> findByInseritaDaUtenteAndStatus(boolean inseritaDaUtente, String status);
    GustoDTO insertGusto(GustoDTO gustoDTO, UserDTO userDTO);
}
