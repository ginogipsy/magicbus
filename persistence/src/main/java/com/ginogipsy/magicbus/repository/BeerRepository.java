package com.ginogipsy.magicbus.repository;

import com.ginogipsy.magicbus.domain.Beer;
import com.ginogipsy.magicbus.domain.enums.BeerType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BeerRepository extends JpaRepository<Beer, Integer> {

    Beer findByName(String name);
    List<Beer> findByBrewery_Name(String name);
    List<Beer> findByBeerType(BeerType beerType);
    List<Beer> findByAvailable(boolean available);
    List<Beer> findByAvailableAndBrewery_Name(boolean available, String breweryName);
    List<Beer> findByAvailableAndBeerType(boolean available, BeerType beerType);
}
