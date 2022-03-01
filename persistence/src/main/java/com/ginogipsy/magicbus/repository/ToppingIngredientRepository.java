package com.ginogipsy.magicbus.repository;

import com.ginogipsy.magicbus.domain.Ingredient;
import com.ginogipsy.magicbus.domain.Topping;
import com.ginogipsy.magicbus.domain.ToppingIngredient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ToppingIngredientRepository extends JpaRepository<ToppingIngredient, Integer> {

    List<ToppingIngredient> findByTopping(Topping topping);

    List<ToppingIngredient> findByIngredient(Ingredient ingredient);

    ToppingIngredient findByToppingAndIngredient(Topping topping, Ingredient ingredient);
}
