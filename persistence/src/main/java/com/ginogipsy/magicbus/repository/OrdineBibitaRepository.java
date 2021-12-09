package com.ginogipsy.magicbus.repository;

import com.ginogipsy.magicbus.domain.DrinkOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdineBibitaRepository extends JpaRepository<DrinkOrder, Integer> {
}
