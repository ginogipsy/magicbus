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
    List<Topping> findByNameContainsAndStatus(final String name, final StatusEnum statusEnum);
    List<Topping> findByStatus(final StatusEnum statusEnum);
    List<Topping> findByBase(final BaseEnum baseEnum);
    List<Topping> findByAvailabilityPeriod(final AvailabilityPeriodEnum availabilityPeriodEnum);
    List<Topping> findByProductCategory(final ProductCategory productCategory);
    List<Topping> findByAvailableAndAvailabilityPeriod(final boolean available, final AvailabilityPeriodEnum availabilityPeriodEnum);
    List<Topping> findByAvailableAndStatus(final boolean available, StatusEnum statusEnum);
    List<Topping> findByUserEntered(final boolean userEntered);
    List<Topping> findByUserEnteredAndStatus(final boolean userEntered, final StatusEnum statusEnum);
}
