package com.ginogipsy.magicbus.repository;

import com.ginogipsy.magicbus.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
