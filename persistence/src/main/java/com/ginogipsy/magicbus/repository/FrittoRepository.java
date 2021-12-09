package com.ginogipsy.magicbus.repository;

import com.ginogipsy.magicbus.domain.Fried;
import com.ginogipsy.magicbus.domain.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface FrittoRepository extends JpaRepository<Fried, Integer> {

    Fried findByNome(String nome);
    List<Fried> findByStatus(Status status);

}
