package com.ginogipsy.magicbus.repository;

import antlr.Token;
import com.ginogipsy.magicbus.domain.RefreshToken;
import com.ginogipsy.magicbus.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
/**
 * @author ginogipsy
 */
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {


    Optional<Token> findByToken(final String token);
    void deleteByUser(final User user);
}
