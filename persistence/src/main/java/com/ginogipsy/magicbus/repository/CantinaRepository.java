package com.ginogipsy.magicbus.repository;

import com.ginogipsy.magicbus.domain.Winery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CantinaRepository extends JpaRepository<Winery, Integer> {
}
