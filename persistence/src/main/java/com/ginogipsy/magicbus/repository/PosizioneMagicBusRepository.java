package com.ginogipsy.magicbus.repository;

import com.ginogipsy.magicbus.domain.MagicbusLocation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PosizioneMagicBusRepository extends JpaRepository<MagicbusLocation, Integer> {
}
