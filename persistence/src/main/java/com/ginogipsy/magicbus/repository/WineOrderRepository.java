package com.ginogipsy.magicbus.repository;

import com.ginogipsy.magicbus.domain.WineOrder;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * @author ginogipsy
 */
public interface WineOrderRepository extends JpaRepository<WineOrder, Integer> {
}
