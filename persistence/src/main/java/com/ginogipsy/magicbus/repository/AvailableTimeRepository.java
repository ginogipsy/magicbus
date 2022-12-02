package com.ginogipsy.magicbus.repository;

import com.ginogipsy.magicbus.domain.AvailableTime;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * @author ginogipsy
 */

public interface AvailableTimeRepository extends JpaRepository<AvailableTime, Integer> {
}
