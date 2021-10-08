package com.ginogipsy.magicbus.repository;

import com.ginogipsy.magicbus.domain.RefreshToken;
import com.ginogipsy.magicbus.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {


    RefreshToken findByToken(String token);
    void deleteByUser(User user);
}
