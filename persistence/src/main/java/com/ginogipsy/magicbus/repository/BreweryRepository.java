package com.ginogipsy.magicbus.repository;

import com.ginogipsy.magicbus.domain.Brewery;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * @author ginogipsy
 */

public interface BreweryRepository extends JpaRepository<Brewery, Integer> {
}
