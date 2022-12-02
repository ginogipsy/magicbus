package com.ginogipsy.magicbus.repository;

import com.ginogipsy.magicbus.domain.Dough;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author ginogipsy
 */

public interface DoughRepository extends JpaRepository<Dough, Integer> {

    Optional<Dough> findByName(final String name);
}
