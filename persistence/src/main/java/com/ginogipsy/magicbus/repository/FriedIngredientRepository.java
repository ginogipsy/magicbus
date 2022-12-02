package com.ginogipsy.magicbus.repository;

import com.ginogipsy.magicbus.domain.Fried;
import com.ginogipsy.magicbus.domain.FriedIngredient;
import com.ginogipsy.magicbus.domain.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author ginogipsy
 */
public interface FriedIngredientRepository extends JpaRepository<FriedIngredient, Integer> {

    List<FriedIngredient> findByFried(final Fried fried);

    List<FriedIngredient> findByIngredient(final Ingredient ingredient);

    Optional<FriedIngredient> findByFriedAndIngredient(final Fried fried, final Ingredient ingredient);

    void deleteByFriedAndIngredient(final Fried fried, final Ingredient ingredient);
}
