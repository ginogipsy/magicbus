package com.ginogipsy.magicbus.repository;

import com.ginogipsy.magicbus.domain.Allergen;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author ginogipsy
 */

public interface AllergenRepository extends JpaRepository<Allergen, Integer> {

    Optional<Allergen> findByName(final String name);
}
