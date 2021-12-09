package com.ginogipsy.magicbus.repository;

import com.ginogipsy.magicbus.domain.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FornitoreRepository extends JpaRepository<Supplier, Integer> {
}
