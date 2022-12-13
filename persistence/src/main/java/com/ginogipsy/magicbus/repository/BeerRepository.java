package com.ginogipsy.magicbus.repository;

import com.ginogipsy.magicbus.domain.Beer;
import com.ginogipsy.magicbus.domain.enums.BeerTypeEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author ginogipsy
 */

public interface BeerRepository extends JpaRepository<Beer, Integer> {

    Optional<Beer> findByName(final String name);
    List<Beer> findByBrewery_Name(final String name);
    List<Beer> findByBeerTypeEnum(final BeerTypeEnum beerTypeEnum);
    List<Beer> findByAvailable(final boolean available);
    List<Beer> findByAvailableAndBrewery_Name(final boolean available, final String breweryName);
    List<Beer> findByAvailableAndBeerTypeEnum(final boolean available, final BeerTypeEnum beerTypeEnum);
}
