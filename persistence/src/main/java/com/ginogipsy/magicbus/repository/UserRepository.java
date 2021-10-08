package com.ginogipsy.magicbus.repository;

import com.ginogipsy.magicbus.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsername(String username);
    User findByEmail(String email);
    List<User> findByUsernameOrEmail(String username, String email);
    User findByCodiceFiscale(String codiceFiscale);
    User findByNumeroCellulare(long numeroCellulare);
    User findByEmailAndUsernameAndNumeroCellulare(String email, String username, long numeroCellulare);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}
