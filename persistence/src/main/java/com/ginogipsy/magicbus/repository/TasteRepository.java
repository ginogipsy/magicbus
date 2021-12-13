package com.ginogipsy.magicbus.repository;

import com.ginogipsy.magicbus.domain.*;
import com.ginogipsy.magicbus.domain.enums.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TasteRepository extends JpaRepository<Topping, Integer> {

    Topping findByName(String name);
    List<Topping> findByNameContains(String name);
    List<Topping> findByNameContainsAndStatus(String name, Status status);
    List<Topping> findByStatus(Status status);
    List<Topping> findByBase(Base base);
    List<Topping> findByAvailabilityPeriod(AvailabilityPeriod availabilityPeriod);
    List<Topping> findByProductCategory(ProductCategory productCategory);
    List<Topping> findByAvailableAndAvailabilityPeriod(boolean available, AvailabilityPeriod availabilityPeriod);
    List<Topping> findByAvailableAndStatus(boolean available, Status status);
    List<Topping> findByUserEntered(boolean userEntered);
    List<Topping> findByUserEnteredAndStatus(boolean userEntered, Status status);
}
