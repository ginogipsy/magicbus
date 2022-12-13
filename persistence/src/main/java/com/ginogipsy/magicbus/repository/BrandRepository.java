package com.ginogipsy.magicbus.repository;

import com.ginogipsy.magicbus.domain.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author ginogipsy
 */

public interface BrandRepository extends JpaRepository<Brand, Integer> {

    Optional<Brand> findByName(final String name);
}
