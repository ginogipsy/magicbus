package com.ginogipsy.magicbus.repository;

import com.ginogipsy.magicbus.domain.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author ginogipsy
 */
public interface IngredientRepository extends JpaRepository<Ingredient, Integer> {

    Optional<Ingredient> findByName(final String name);

    List<Ingredient> findByNameContains(final String name);
}
