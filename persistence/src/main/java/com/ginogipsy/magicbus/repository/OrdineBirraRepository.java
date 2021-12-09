package com.ginogipsy.magicbus.repository;

import com.ginogipsy.magicbus.domain.BeerOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdineBirraRepository extends JpaRepository<BeerOrder, Integer> {
}
