package com.ginogipsy.magicbus.repository;

import com.ginogipsy.magicbus.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
/**
 * @author ginogipsy
 */
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsername(final String username);
    User findByEmail(String email);
    List<User> findByUsernameOrEmail(final String username, final String email);
    User findByFiscalCode(final String fiscalCode);
    User findByCellNumber(final String cellNumber);
    User findByEmailAndUsernameAndCellNumber(final String email, final String username, final String cellNumber);
    Boolean existsByUsername(final String username);
    Boolean existsByEmail(final String email);
}
