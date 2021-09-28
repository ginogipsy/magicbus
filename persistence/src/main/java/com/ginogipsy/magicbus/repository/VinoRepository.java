package com.ginogipsy.magicbus.repository;

import com.ginogipsy.magicbus.domain.Cantina;
import com.ginogipsy.magicbus.domain.QualitaVino;
import com.ginogipsy.magicbus.domain.Vino;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VinoRepository extends JpaRepository<Vino, Integer> {

    Vino findByNome(String nome);
    List<Vino> findByCantina_Nome(String nomeCantina);
    List<Vino> findByQualitaVino(QualitaVino qualitaVino);
    List<Vino> findByDisponibile(Boolean disponibile);
    List<Vino> findByDisponibileAndCantina_Nome(String nomeCantina);
    List<Vino> findByDisponibileAndQualitaVino(Boolean disponibile, QualitaVino qualitaVino);

}
