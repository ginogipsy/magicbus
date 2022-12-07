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
    List<Topping> findByNameContainsAndStatusEnum(final String name, final StatusEnum statusEnum);
    List<Topping> findByStatusEnum(final StatusEnum statusEnum);
    List<Topping> findByBaseEnum(final BaseEnum baseEnum);
    List<Topping> findByAvailabilityPeriodEnum(final AvailabilityPeriodEnum availabilityPeriodEnum);
    List<Topping> findByProductCategoryEnum(final ProductCategoryEnum productCategoryEnum);
    List<Topping> findByAvailableAndAvailabilityPeriodEnum(final boolean available, final AvailabilityPeriodEnum availabilityPeriodEnum);
    List<Topping> findByAvailableAndStatusEnum(final boolean available, StatusEnum statusEnum);
    List<Topping> findByUserEntered(final boolean userEntered);
    List<Topping> findByUserEnteredAndStatusEnum(final boolean userEntered, final StatusEnum statusEnum);
}
