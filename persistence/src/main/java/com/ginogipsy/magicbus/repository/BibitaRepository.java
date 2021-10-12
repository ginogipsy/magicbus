package com.ginogipsy.magicbus.repository;

import com.ginogipsy.magicbus.domain.Bibita;
import com.ginogipsy.magicbus.domain.Status;
import com.ginogipsy.magicbus.domain.TipologiaBibite;
import com.ginogipsy.magicbus.domain.TipologiaMenu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BibitaRepository extends JpaRepository<Bibita, Integer> {

    Bibita findByNome(String nome);
    List<Bibita> findByTipologiaBibite(TipologiaBibite tipologiaBibite);
    List<Bibita> findByStatus(Status status);
    List<Bibita> findByStatusAndTipologiaBibite(Status status, TipologiaBibite tipologiaBibite);
    List<Bibita> findByStatusAndTipologiaMenu(Status status, TipologiaMenu tipologiaMenu);
}
