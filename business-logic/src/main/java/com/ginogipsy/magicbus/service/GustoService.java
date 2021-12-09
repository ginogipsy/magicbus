package com.ginogipsy.magicbus.service;

import com.ginogipsy.magicbus.dto.TasteDTO;
import com.ginogipsy.magicbus.dto.UserDTO;

import java.util.List;

public interface GustoService {

    List<TasteDTO> findGustiByStatus(String status);
    TasteDTO findGustoByNome(String nome);
    List<TasteDTO> findGustiNameContains(String nome);
    List<TasteDTO> findByBase(String base);
    List<TasteDTO> findByCategoriaProdotto(String categoriaProdotto);
    List<TasteDTO> findByPeriodoDisponibilita(String periodoDisponibilita);
    List<TasteDTO> findByDisponibilita(boolean disponibilita, String status);
    List<TasteDTO> findByDisponibilitaAndPeriodoDisponibilita(boolean disponibile, String periodoDisponibilita);
    List<TasteDTO> findByInseritaDaUtente(boolean inseritaDaUtente);
    List<TasteDTO> findByInseritaDaUtenteAndStatus(boolean inseritaDaUtente, String status);
    TasteDTO insertGusto(TasteDTO tasteDTO, UserDTO userDTO);
}
