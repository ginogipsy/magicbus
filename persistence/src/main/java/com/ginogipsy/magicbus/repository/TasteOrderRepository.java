package com.ginogipsy.magicbus.repository;

import com.ginogipsy.magicbus.domain.ToppingOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TasteOrderRepository extends JpaRepository<ToppingOrder, Integer> {
}
