package com.ginogipsy.magicbus.repository;

import com.ginogipsy.magicbus.domain.MagicbusLocation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MagicbusLocationRepository extends JpaRepository<MagicbusLocation, Integer> {
}
