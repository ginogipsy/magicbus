package com.ginogipsy.magicbus.repository;

import com.ginogipsy.magicbus.domain.ToppingIngredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TasteIngredientRepository extends JpaRepository<ToppingIngredient, Integer> {
}
