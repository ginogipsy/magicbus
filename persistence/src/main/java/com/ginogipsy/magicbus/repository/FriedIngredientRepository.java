package com.ginogipsy.magicbus.repository;

import com.ginogipsy.magicbus.domain.Fried;
import com.ginogipsy.magicbus.domain.FriedIngredient;
import com.ginogipsy.magicbus.domain.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FriedIngredientRepository extends JpaRepository<FriedIngredient, Integer> {

    List<FriedIngredient> findByFried(Fried fried);

    List<FriedIngredient> findByIngredient(Ingredient ingredient);

    FriedIngredient findByFriedAndIngredient(Fried fried, Ingredient ingredient);
}
