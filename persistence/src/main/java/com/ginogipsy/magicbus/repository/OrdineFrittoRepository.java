package com.ginogipsy.magicbus.repository;

import com.ginogipsy.magicbus.domain.FriedOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdineFrittoRepository extends JpaRepository<FriedOrder, Integer> {
}
