package com.ginogipsy.magicbus.repository;

import com.ginogipsy.magicbus.domain.enums.WineQuality;
import com.ginogipsy.magicbus.domain.Wine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VinoRepository extends JpaRepository<Wine, Integer> {

    Wine findByNome(String nome);
    List<Wine> findByCantina_Nome(String nomeCantina);
    List<Wine> findByQualitaVino(WineQuality wineQuality);
    List<Wine> findByDisponibile(Boolean disponibile);
    List<Wine> findByDisponibileAndCantina_Nome(Boolean disponibile, String nomeCantina);
    List<Wine> findByDisponibileAndQualitaVino(Boolean disponibile, WineQuality wineQuality);

}
