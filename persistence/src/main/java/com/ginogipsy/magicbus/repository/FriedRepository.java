package com.ginogipsy.magicbus.repository;

import com.ginogipsy.magicbus.domain.Fried;
import com.ginogipsy.magicbus.domain.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface FriedRepository extends JpaRepository<Fried, Integer> {

    Fried findByName(String name);
    List<Fried> findByStatus(Status status);

}
