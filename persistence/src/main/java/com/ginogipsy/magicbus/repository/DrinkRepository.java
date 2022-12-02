package com.ginogipsy.magicbus.repository;

import com.ginogipsy.magicbus.domain.Drink;
import com.ginogipsy.magicbus.domain.enums.Status;
import com.ginogipsy.magicbus.domain.enums.DrinkType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author ginogipsy
 */
public interface DrinkRepository extends JpaRepository<Drink, Integer> {

    Optional<Drink> findByName(final String name);
    List<Drink> findByDrinkType(final DrinkType drinkType);
    List<Drink> findByStatus(final Status status);
    List<Drink> findByStatusAndDrinkType(final Status status, final DrinkType drinkType);
}
