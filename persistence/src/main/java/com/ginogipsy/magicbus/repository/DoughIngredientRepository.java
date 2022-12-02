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

    List<DoughIngredient> findByDough(Dough dough);

    List<DoughIngredient> findByIngredient(Ingredient ingredient);

    Optional<DoughIngredient> findByDoughAndIngredient(Dough dough, Ingredient ingredient);

    void deleteByDoughAndIngredient(Dough dough, Ingredient ingredient);
}
