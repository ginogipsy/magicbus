package com.ginogipsy.magicbus.repository;

import com.ginogipsy.magicbus.domain.Beer;
import com.ginogipsy.magicbus.domain.enums.BeerType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BirraRepository extends JpaRepository<Beer, Integer> {

    Beer findByNome(String nome);
    List<Beer> findByBirrificio_Nome(String nome);
    List<Beer> findByTipologiaBirra(BeerType beerType);
    List<Beer> findByDisponibile(boolean disponibile);
    List<Beer> findByDisponibileAndAndBirrificio_Nome(boolean disponibile, String nomeBirrificio);
    List<Beer> findByDisponibileAndTipologiaBirra(boolean disponibile, BeerType beerType);
}
