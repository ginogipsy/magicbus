package com.ginogipsy.magicbus.repository;

import com.ginogipsy.magicbus.domain.Dough;
import com.ginogipsy.magicbus.domain.DoughIngredient;
import com.ginogipsy.magicbus.domain.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author ginogipsy
 */

public interface DoughIngredientRepository extends JpaRepository<DoughIngredient, Integer> {

    List<DoughIngredient> findByDough(final Dough dough);

    List<DoughIngredient> findByIngredient(final Ingredient ingredient);

    Optional<DoughIngredient> findByDoughAndIngredient(final Dough dough, final Ingredient ingredient);

    void deleteByDoughAndIngredient(final Dough dough, final Ingredient ingredient);
}
