package com.ginogipsy.magicbus.repository;

import com.ginogipsy.magicbus.domain.Drink;
import com.ginogipsy.magicbus.domain.enums.StatusEnum;
import com.ginogipsy.magicbus.domain.enums.DrinkTypeEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author ginogipsy
 */
public interface DrinkRepository extends JpaRepository<Drink, Integer> {

    Optional<Drink> findByName(final String name);
    List<Drink> findByDrinkType(final DrinkTypeEnum drinkTypeEnum);
    List<Drink> findByStatus(final StatusEnum statusEnum);
    List<Drink> findByStatusAndDrinkType(final StatusEnum statusEnum, final DrinkTypeEnum drinkTypeEnum);
}
