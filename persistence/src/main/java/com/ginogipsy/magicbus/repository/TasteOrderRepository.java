package com.ginogipsy.magicbus.repository;

import com.ginogipsy.magicbus.domain.ToppingOrder;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * @author ginogipsy
 */
public interface TasteOrderRepository extends JpaRepository<ToppingOrder, Integer> {
}
