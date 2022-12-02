package com.ginogipsy.magicbus.repository;

import com.ginogipsy.magicbus.domain.Ingredient;
import com.ginogipsy.magicbus.domain.Topping;
import com.ginogipsy.magicbus.domain.ToppingIngredient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author ginogipsy
 */
public interface ToppingIngredientRepository extends JpaRepository<ToppingIngredient, Integer> {

    List<ToppingIngredient> findByTopping(final Topping topping);

    List<ToppingIngredient> findByIngredient(final Ingredient ingredient);

    Optional<ToppingIngredient> findByToppingAndIngredient(final Topping topping, final Ingredient ingredient);

    void deleteByToppingAndIngredient(final Topping topping, final Ingredient ingredient);
}
