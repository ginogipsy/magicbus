package com.ginogipsy.magicbus.repository;

import com.ginogipsy.magicbus.domain.Allergen;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AllergeneRepository extends JpaRepository<Allergen, Integer> {
}
