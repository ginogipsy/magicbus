package com.ginogipsy.magicbus.repository;

import com.ginogipsy.magicbus.domain.Bibita;
import com.ginogipsy.magicbus.domain.enums.Status;
import com.ginogipsy.magicbus.domain.enums.DrinkType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BibitaRepository extends JpaRepository<Bibita, Integer> {

    Bibita findByNome(String nome);
    List<Bibita> findByTipologia(DrinkType tipologiaBibite);
    List<Bibita> findByStatus(Status status);
    List<Bibita> findByStatusAndTipologia(Status status, DrinkType tipologiaBibite);
}
