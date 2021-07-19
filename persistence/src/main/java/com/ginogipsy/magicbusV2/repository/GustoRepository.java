package com.ginogipsy.magicbusV2.repository;

import com.ginogipsy.magicbusV2.domain.Gusto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GustoRepository extends JpaRepository<Gusto, Integer> {

    Gusto findByNome(String nome);
}
