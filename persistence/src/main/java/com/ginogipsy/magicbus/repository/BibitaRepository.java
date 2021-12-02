package com.ginogipsy.magicbus.repository;

import com.ginogipsy.magicbus.domain.Bibita;
import com.ginogipsy.magicbus.domain.enums.Status;
import com.ginogipsy.magicbus.domain.enums.TipologiaBibita;
import com.ginogipsy.magicbus.domain.enums.TipologiaMenu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BibitaRepository extends JpaRepository<Bibita, Integer> {

    Bibita findByNome(String nome);
    List<Bibita> findByTipologia(TipologiaBibita tipologiaBibite);
    List<Bibita> findByStatus(Status status);
    List<Bibita> findByStatusAndTipologia(Status status, TipologiaBibita tipologiaBibite);
    List<Bibita> findByStatusAndTipologiaMenu(Status status, TipologiaMenu tipologiaMenu);
}
