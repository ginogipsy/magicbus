package com.ginogipsy.magicbusV2.repository;

import com.ginogipsy.magicbusV2.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsername(String username);
    User findByEmail(String email);
    List<User> findByUsernameOrEmail(String username, String email);
    User findByCodiceFiscale(String codiceFiscale);
    User findByNumeroCellulare(long numeroCellulare);
}
