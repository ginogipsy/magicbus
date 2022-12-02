package com.ginogipsy.magicbus.repository;

import com.ginogipsy.magicbus.domain.*;
import com.ginogipsy.magicbus.domain.enums.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author ginogipsy
 */
public interface TasteRepository extends JpaRepository<Topping, Integer> {

    Optional<Topping> findByName(final String name);
    List<Topping> findByNameContains(final String name);
    List<Topping> findByNameContainsAndStatus(final String name, final Status status);
    List<Topping> findByStatus(final Status status);
    List<Topping> findByBase(final Base base);
    List<Topping> findByAvailabilityPeriod(final AvailabilityPeriod availabilityPeriod);
    List<Topping> findByProductCategory(final ProductCategory productCategory);
    List<Topping> findByAvailableAndAvailabilityPeriod(final boolean available, final AvailabilityPeriod availabilityPeriod);
    List<Topping> findByAvailableAndStatus(final boolean available, Status status);
    List<Topping> findByUserEntered(final boolean userEntered);
    List<Topping> findByUserEnteredAndStatus(final boolean userEntered, final  Status status);
}
