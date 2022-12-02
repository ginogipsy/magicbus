package com.ginogipsy.magicbus.repository;

import com.ginogipsy.magicbus.domain.PaymentType;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * @author ginogipsy
 */
public interface PaymentTypeRepository extends JpaRepository<PaymentType, Integer> {
}
