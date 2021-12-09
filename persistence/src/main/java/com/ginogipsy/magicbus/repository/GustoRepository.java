package com.ginogipsy.magicbus.repository;

import com.ginogipsy.magicbus.domain.*;
import com.ginogipsy.magicbus.domain.enums.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GustoRepository extends JpaRepository<Taste, Integer> {

    Taste findByNome(String nome);
    List<Taste> findByNomeContains(String nome);
    List<Taste> findByNomeContainsAndStatus(String nome, Status status);
    List<Taste> findByStatus(Status status);
    List<Taste> findByBase(Base base);
    List<Taste> findByPeriodoDisponibilita(AvailabilityPeriod availabilityPeriod);
    List<Taste> findByCategoriaProdotto(ProductCategory productCategory);
    List<Taste> findByDisponibileAndPeriodoDisponibilita(Boolean disponibile, AvailabilityPeriod availabilityPeriod);
    List<Taste> findByDisponibileAndStatus(Boolean disponibile, Status status);

    List<Taste> findByGustoUtente(boolean gustoUtente);
    List<Taste> findByGustoUtenteAndStatus(boolean inseritaDaUtente, Status status);
}
