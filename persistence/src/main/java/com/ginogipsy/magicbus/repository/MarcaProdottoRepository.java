package com.ginogipsy.magicbus.repository;

import com.ginogipsy.magicbus.domain.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarcaProdottoRepository extends JpaRepository<Brand, Integer> {
}
