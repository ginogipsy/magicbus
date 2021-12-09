package com.ginogipsy.magicbus.repository;

import com.ginogipsy.magicbus.domain.OrderPayment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdinePagamentoRepository extends JpaRepository<OrderPayment, Integer> {
}
