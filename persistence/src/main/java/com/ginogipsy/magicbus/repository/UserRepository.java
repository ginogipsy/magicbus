package com.ginogipsy.magicbus.repository;

import com.ginogipsy.magicbus.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsername(String username);
    User findByEmail(String email);
    List<User> findByUsernameOrEmail(String username, String email);
    User findByFiscalCode(String fiscalCode);
    User findByCellNumber(String cellNumber);
    User findByEmailAndUsernameAndCellNumber(String email, String username, String cellNumber);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}
