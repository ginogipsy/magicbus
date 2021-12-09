package com.ginogipsy.magicbus.repository;

import com.ginogipsy.magicbus.domain.Drink;
import com.ginogipsy.magicbus.domain.enums.Status;
import com.ginogipsy.magicbus.domain.enums.DrinkType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BibitaRepository extends JpaRepository<Drink, Integer> {

    Drink findByNome(String nome);
    List<Drink> findByTipologia(DrinkType tipologiaBibite);
    List<Drink> findByStatus(Status status);
    List<Drink> findByStatusAndTipologia(Status status, DrinkType tipologiaBibite);
}
