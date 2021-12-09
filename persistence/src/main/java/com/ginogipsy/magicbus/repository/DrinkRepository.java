package com.ginogipsy.magicbus.repository;

import com.ginogipsy.magicbus.domain.Drink;
import com.ginogipsy.magicbus.domain.enums.Status;
import com.ginogipsy.magicbus.domain.enums.DrinkType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DrinkRepository extends JpaRepository<Drink, Integer> {

    Drink findByName(String name);
    List<Drink> findByDrinkType(DrinkType drinkType);
    List<Drink> findByStatus(Status status);
    List<Drink> findByStatusAndType(Status status, DrinkType drinkType);
}
