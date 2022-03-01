package com.ginogipsy.magicbus.repository;

import com.ginogipsy.magicbus.domain.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IngredientRepository extends JpaRepository<Ingredient, Integer> {

    Ingredient findByName(String name);

    List<Ingredient> findByNameContains(String name);
}
