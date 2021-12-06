package com.ginogipsy.magicbus.repository;

import com.ginogipsy.magicbus.domain.Fritto;
import com.ginogipsy.magicbus.domain.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface FrittoRepository extends JpaRepository<Fritto, Integer> {

    Fritto findByNome(String nome);
    List<Fritto> findByStatus(Status status);

}
