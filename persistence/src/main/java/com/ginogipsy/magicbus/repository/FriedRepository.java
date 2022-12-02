package com.ginogipsy.magicbus.repository;

import com.ginogipsy.magicbus.domain.Fried;
import com.ginogipsy.magicbus.domain.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author ginogipsy
 */
public interface FriedRepository extends JpaRepository<Fried, Integer> {

    Optional<Fried> findByName(final String name);
    List<Fried> findByStatus(final Status status);

}
