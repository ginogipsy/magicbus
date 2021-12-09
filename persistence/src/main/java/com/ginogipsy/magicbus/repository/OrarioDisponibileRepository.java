package com.ginogipsy.magicbus.repository;

import com.ginogipsy.magicbus.domain.AvailableTime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrarioDisponibileRepository extends JpaRepository<AvailableTime, Integer> {
}
