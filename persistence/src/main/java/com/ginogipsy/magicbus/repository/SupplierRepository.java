package com.ginogipsy.magicbus.repository;

import com.ginogipsy.magicbus.domain.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author ginogipsy
 */
public interface SupplierRepository extends JpaRepository<Supplier, Integer> {

    Optional<Supplier> findByName(final String name);
}
