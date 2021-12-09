package com.ginogipsy.magicbus.repository;

import com.ginogipsy.magicbus.domain.*;
import com.ginogipsy.magicbus.domain.enums.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TasteRepository extends JpaRepository<Taste, Integer> {

    Taste findByName(String name);
    List<Taste> findByNameContains(String name);
    List<Taste> findByNameContainsAndStatus(String name, Status status);
    List<Taste> findByStatus(Status status);
    List<Taste> findByBase(Base base);
    List<Taste> findByAvailabilityPeriod(AvailabilityPeriod availabilityPeriod);
    List<Taste> findByProductCategory(ProductCategory productCategory);
    List<Taste> findByAvailableAndAvailabilityPeriod(boolean available, AvailabilityPeriod availabilityPeriod);
    List<Taste> findByAvailableAndStatus(boolean available, Status status);
    List<Taste> findByUserEntered(boolean userEntered);
    List<Taste> findByUserEnteredAndStatus(boolean userEntered, Status status);
}
