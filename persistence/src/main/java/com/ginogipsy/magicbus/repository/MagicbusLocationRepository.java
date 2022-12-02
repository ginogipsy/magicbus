package com.ginogipsy.magicbus.repository;

import com.ginogipsy.magicbus.domain.MagicbusLocation;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * @author ginogipsy
 */
public interface MagicbusLocationRepository extends JpaRepository<MagicbusLocation, Integer> {
}
