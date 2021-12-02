package com.ginogipsy.magicbus.service;

import com.ginogipsy.magicbus.customexception.TokenRefreshException;
import com.ginogipsy.magicbus.dto.RefreshTokenDTO;
import com.ginogipsy.magicbus.marshall.MapperFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenService {

    @Value("${bezkoder.app.jwtRefreshExpirationMs}")
    private Long refreshTokenDurationMs;
    private final MapperFactory mapperFactory;


    public RefreshTokenService(MapperFactory mapperFactory) {
        this.mapperFactory = mapperFactory;
    }

    public Optional<RefreshTokenDTO> findByToken(final String token){
        return Optional.ofNullable(mapperFactory.getRefreshTokenMapper().findByToken(token));
    }

    public RefreshTokenDTO createRefreshToken(final Integer userId){
        RefreshTokenDTO refreshTokenDTO = new RefreshTokenDTO();

        refreshTokenDTO.setUser(mapperFactory.getUserMapper().findById(userId));
        refreshTokenDTO.setExpiryDate(Instant.now().plusMillis(refreshTokenDurationMs));
        refreshTokenDTO.setToken(UUID.randomUUID().toString());

        return mapperFactory.getRefreshTokenMapper().save(refreshTokenDTO);
    }

    public RefreshTokenDTO verifyExpiration(RefreshTokenDTO refreshTokenDTO){
        if(refreshTokenDTO.getExpiryDate().compareTo(Instant.now()) < 0){
            mapperFactory.getRefreshTokenMapper().delete(refreshTokenDTO);
            throw new TokenRefreshException(refreshTokenDTO.getToken(), "Refresh token was expired. Please make a new signin request");
        }
        return refreshTokenDTO;
    }

    @Transactional
    public void deleteByUserId(Integer userId){
        mapperFactory.getRefreshTokenMapper().deleteByUser(userId);
    }




}