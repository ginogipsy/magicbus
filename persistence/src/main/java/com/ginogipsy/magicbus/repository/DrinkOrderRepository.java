package com.ginogipsy.magicbus.repository;

import com.ginogipsy.magicbus.domain.DrinkOrder;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * @author ginogipsy
 */
public interface DrinkOrderRepository extends JpaRepository<DrinkOrder, Integer> {
}
