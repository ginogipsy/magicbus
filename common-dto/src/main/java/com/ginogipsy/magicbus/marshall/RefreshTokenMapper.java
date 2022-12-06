package com.ginogipsy.magicbus.marshall;

import com.ginogipsy.magicbus.domain.RefreshToken;
import com.ginogipsy.magicbus.dto.RefreshTokenDTO;
import com.ginogipsy.magicbus.repository.RefreshTokenRepository;
import com.ginogipsy.magicbus.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author ginogipsy
 */

@Slf4j
@Component
@RequiredArgsConstructor
public class RefreshTokenMapper {

    private final ModelMapper modelMapper;
    private final RefreshTokenRepository refreshTokenRepository;
    private final UserRepository userRepository;

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

    public Optional<RefreshTokenDTO> convertToDTO(final Optional<RefreshToken> refreshToken) {
        return Optional.ofNullable(refreshToken)
                .map(rt -> modelMapper.map(rt, RefreshTokenDTO.class));
    }

    public Optional<RefreshToken> convertToEntity(final Optional<RefreshTokenDTO> refreshTokenDTO) {
        return Optional.ofNullable(refreshTokenDTO)
                .map(rt -> modelMapper.map(rt, RefreshToken.class));
    }

    public Optional<RefreshTokenDTO> findById(final Long id) {
        log.info("RefreshTokenMapper - findById() -> Searching refresh token where id is {}..", id);
        return Optional.ofNullable(id)
                .map(refreshTokenRepository::getReferenceById)
                .map(this::convertToDTO);
    }

    public Optional<RefreshTokenDTO> findByToken(final String token) {
        log.info("RefreshTokenMapper - findByToken() -> Searching refresh token where token is {}..", token);
        return Optional.ofNullable(token)
                .flatMap(refreshTokenRepository::findByToken)
                .map(this::convertToDTO);
    }

    public Optional<RefreshTokenDTO> save(final RefreshTokenDTO refreshTokenDTO) {
        log.info("RefreshTokenMapper - save() -> Saving refresh token..");
        return Optional.ofNullable(refreshTokenDTO)
                .map(this::convertToEntity)
                .map(refreshTokenRepository::save)
                .map(this::convertToDTO);
    }

    public void delete(final RefreshTokenDTO refreshTokenDTO) {
        log.info("RefreshTokenMapper - delete() -> Deleting refresh token..");
        Optional.ofNullable(refreshTokenDTO)
                .map(this::convertToEntity)
                .ifPresent(refreshTokenRepository::delete);
    }

    public void deleteByUser(final Integer userId) {
        log.info("deleting refresh token by user id..");
        Optional.ofNullable(userId)
                .map(userRepository::getReferenceById)
                .ifPresent(refreshTokenRepository::deleteByUser);
    }


}
