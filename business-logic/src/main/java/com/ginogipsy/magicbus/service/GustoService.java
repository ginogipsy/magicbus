package com.ginogipsy.magicbus.service;

import com.ginogipsy.magicbus.customexception.notfound.*;
import com.ginogipsy.magicbus.dto.GustoDTO;

import java.util.List;

public interface GustoService {

    List<GustoDTO> findGustiByStatus(String status) throws StatusProductsNotFoundException;
    GustoDTO findGustoByNome(String nome) throws GustoNotFoundException;
    List<GustoDTO> findByBase(String base) throws BaseNotFoundException;
    List<GustoDTO> findByCategoriaProdotto(String categoriaProdotto) throws CategoriaProdottoNotFoundException;
    List<GustoDTO> findByPeriodoDisponibilita(String periodoDisponibilita) throws PeriodoDisponibilitaNotFoundException;
    List<GustoDTO> findByDisponibilita(Boolean disponibilita, String status) throws StatusProductsNotFoundException;
    List<GustoDTO> findByDisponibilitaAndPeriodoDisponibilita(Boolean disponibile, String periodoDisponibilita) throws PeriodoDisponibilitaNotFoundException;
    List<GustoDTO> findByInseritaDaUtente(Boolean inseritaDaUtente);
    List<GustoDTO> findByInseritaDaUtenteAndStatus(Boolean inseritaDaUtente, String status) throws StatusProductsNotFoundException;
}