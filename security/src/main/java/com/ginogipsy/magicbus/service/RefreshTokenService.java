package com.ginogipsy.magicbus.service;

import com.ginogipsy.magicbus.dto.RefreshTokenDTO;
import com.ginogipsy.magicbus.dto.UserDTO;
import com.ginogipsy.magicbus.exceptionhandler.BeErrorCodeEnum;
import com.ginogipsy.magicbus.exceptionhandler.MagicbusException;
import com.ginogipsy.magicbus.marshall.MapperFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import static com.ginogipsy.magicbus.exceptionhandler.BeErrorCodeEnum.REFRESH_TOKEN_EXPIRED;

/**
 * @author ginogipsy
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    @Value("${bezkoder.app.jwtRefreshExpirationMs}")
    private Long refreshTokenDurationMs;
    private final MapperFactory mapperFactory;

    public Optional<RefreshTokenDTO> findByToken(final String token){
        log.info("RefreshTokenService - findByToken() -> Searching refresh token where token is '{}' ..", token);
        return mapperFactory.getRefreshTokenMapper().findByToken(token);
    }

    public RefreshTokenDTO createRefreshToken(final Integer userId){

        RefreshTokenDTO refreshTokenDTO = new RefreshTokenDTO();

        final UserDTO userDTO = mapperFactory.getUserMapper()
                .findById(userId)
                .orElseThrow(() -> new MagicbusException(BeErrorCodeEnum.USER_NOT_FOUND));

        log.info("RefreshTokenService - createRefreshToken() -> Creating refresh token where user is '{}' ..", userDTO.getUsername());

        refreshTokenDTO.setUser(userDTO);
        refreshTokenDTO.setExpiryDate(Instant.now().plusMillis(refreshTokenDurationMs));
        refreshTokenDTO.setToken(UUID.randomUUID().toString());

        return  mapperFactory.getRefreshTokenMapper()
                .save(refreshTokenDTO)
                .orElseThrow(() -> new MagicbusException(BeErrorCodeEnum.SAVE_FAILED));
    }

    public RefreshTokenDTO verifyExpiration(final RefreshTokenDTO refreshTokenDTO){
        log.info("RefreshTokenService - verifyExpiration() -> Verifying refresh token expiration ..");

        if(refreshTokenDTO.getExpiryDate().compareTo(Instant.now()) < 0){
            log.error("RefreshTokenService - verifyExpiration() -> Refresh token is expired!");
            log.warn("RefreshTokenService - verifyExpiration() -> Deleting refresh token ..");
            mapperFactory.getRefreshTokenMapper().delete(refreshTokenDTO);
            throw new MagicbusException(REFRESH_TOKEN_EXPIRED);
        }
        log.info("RefreshTokenService - verifyExpiration() -> Refresh token is good yet!");
        return refreshTokenDTO;
    }

    @Transactional
    public void deleteByUserId(final Integer userId){
        log.info("RefreshTokenService - deleteByUserId() -> Deleting refresh token ..");
        Optional.ofNullable(userId)
                .ifPresent(id ->  mapperFactory.getRefreshTokenMapper().deleteByUser(id));

    }
}
