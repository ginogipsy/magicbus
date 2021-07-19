package com.ginogipsy.magicbusV2.repository;

import com.ginogipsy.magicbusV2.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
}
