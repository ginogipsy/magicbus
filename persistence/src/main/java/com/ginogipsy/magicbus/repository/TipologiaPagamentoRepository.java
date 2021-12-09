package com.ginogipsy.magicbus.repository;

import com.ginogipsy.magicbus.domain.PaymentType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipologiaPagamentoRepository extends JpaRepository<PaymentType, Integer> {
}
