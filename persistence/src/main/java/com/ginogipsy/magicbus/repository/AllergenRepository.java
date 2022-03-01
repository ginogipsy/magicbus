package com.ginogipsy.magicbus.repository;

import com.ginogipsy.magicbus.domain.Allergen;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AllergenRepository extends JpaRepository<Allergen, Integer> {

    Allergen findByName(String name);
}
