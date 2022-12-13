package com.ginogipsy.magicbus.repository;

import com.ginogipsy.magicbus.domain.BeerOrder;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * @author ginogipsy
 */

public interface BeerOrderRepository extends JpaRepository<BeerOrder, Integer> {
}
