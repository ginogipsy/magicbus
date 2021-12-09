package com.ginogipsy.magicbus.repository;

import com.ginogipsy.magicbus.domain.Birra;
import com.ginogipsy.magicbus.domain.enums.BeerType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BirraRepository extends JpaRepository<Birra, Integer> {

    Birra findByNome(String nome);
    List<Birra> findByBirrificio_Nome(String nome);
    List<Birra> findByTipologiaBirra(BeerType beerType);
    List<Birra> findByDisponibile(boolean disponibile);
    List<Birra> findByDisponibileAndAndBirrificio_Nome(boolean disponibile, String nomeBirrificio);
    List<Birra> findByDisponibileAndTipologiaBirra(boolean disponibile, BeerType beerType);
}
