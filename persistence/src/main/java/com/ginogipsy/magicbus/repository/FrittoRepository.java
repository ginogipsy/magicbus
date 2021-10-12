package com.ginogipsy.magicbus.repository;

import com.ginogipsy.magicbus.domain.Fritto;
import com.ginogipsy.magicbus.domain.Status;
import com.ginogipsy.magicbus.domain.TipologiaMenu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface FrittoRepository extends JpaRepository<Fritto, Integer> {

    Fritto findByNome(String nome);
    List<Fritto> findByStatus(Status status);
    List<Fritto> findByTipologiaMenuAndDisponibile(TipologiaMenu tipologiaMenu, Boolean disponibile);
}
