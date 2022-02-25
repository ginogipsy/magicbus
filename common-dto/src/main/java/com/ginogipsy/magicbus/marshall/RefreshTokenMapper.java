package com.ginogipsy.magicbus.marshall;

import com.ginogipsy.magicbus.domain.RefreshToken;
import com.ginogipsy.magicbus.dto.RefreshTokenDTO;
import com.ginogipsy.magicbus.repository.RefreshTokenRepository;
import com.ginogipsy.magicbus.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
public class RefreshTokenMapper {

    private final ModelMapper modelMapper;
    private final RefreshTokenRepository refreshTokenRepository;
    private final UserRepository userRepository;

    public RefreshTokenMapper(ModelMapper modelMapper, RefreshTokenRepository refreshTokenRepository, UserRepository userRepository) {
        this.modelMapper = modelMapper;
        this.refreshTokenRepository = refreshTokenRepository;
        this.userRepository = userRepository;
    }

    public RefreshTokenDTO convertToDTO(final RefreshToken refreshToken) {
        return Optional.ofNullable(refreshToken)
                .map(rt -> modelMapper.map(rt, RefreshTokenDTO.class))
                .orElse(null);
    }

    public RefreshToken convertToEntity(final RefreshTokenDTO refreshTokenDTO) {
        return Optional.ofNullable(refreshTokenDTO)
                .map(rt -> modelMapper.map(rt, RefreshToken.class))
                .orElse(null);
    }

    public RefreshTokenDTO findById(final Long id) {
        log.info("Searching refresh token where id is " + id+ "..");
        return Optional.ofNullable(id)
                .map(refreshTokenRepository::getById)
                .map(this::convertToDTO)
                .orElse(null);
    }

    public RefreshTokenDTO findByToken(final String token) {
        log.info("Searching refresh token where token is " + token+ "..");
        return Optional.ofNullable(token)
                .map(refreshTokenRepository::findByToken)
                .map(this::convertToDTO)
                .orElse(null);
    }

    public RefreshTokenDTO save(final RefreshTokenDTO refreshTokenDTO) {
        log.info("Saving refresh token..");
        return Optional.ofNullable(refreshTokenDTO)
                .map(this::convertToEntity)
                .map(refreshTokenRepository::save)
                .map(this::convertToDTO)
                .orElse(null);
    }

    public void delete(final RefreshTokenDTO refreshTokenDTO) {
        log.info("deleting refresh token..");
        Optional.ofNullable(refreshTokenDTO)
                .map(this::convertToEntity)
                .ifPresent(refreshTokenRepository::delete);
    }

    public void deleteByUser(final Integer userId) {
        log.info("deleting refresh token by user id..");
        Optional.ofNullable(userId)
                .map(userRepository::getById)
                .ifPresent(refreshTokenRepository::deleteByUser);
    }


}
