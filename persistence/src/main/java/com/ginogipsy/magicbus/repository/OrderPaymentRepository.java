package com.ginogipsy.magicbus.repository;

import com.ginogipsy.magicbus.domain.PaymentOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderPaymentRepository extends JpaRepository<PaymentOrder, Integer> {
}
