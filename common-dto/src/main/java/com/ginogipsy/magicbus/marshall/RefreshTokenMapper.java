package com.ginogipsy.magicbus.marshall;

import com.ginogipsy.magicbus.domain.RefreshToken;
import com.ginogipsy.magicbus.dto.RefreshTokenDTO;
import com.ginogipsy.magicbus.repository.RefreshTokenRepository;
import com.ginogipsy.magicbus.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


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

    public RefreshTokenDTO convertToDTO(final RefreshToken refreshToken){
        return (refreshToken != null) ? modelMapper.map(refreshToken, RefreshTokenDTO.class) : null;
    }

    public RefreshToken convertToEntity(final RefreshTokenDTO refreshTokenDTO){
        return (refreshTokenDTO != null) ? modelMapper.map(refreshTokenDTO, RefreshToken.class) : null;
    }

    public RefreshTokenDTO findById(final Long id){
        return convertToDTO(refreshTokenRepository.getById(id));
    }

    public RefreshTokenDTO findByToken(final String token){
        return convertToDTO(refreshTokenRepository.findByToken(token));
    }

    public RefreshTokenDTO save(final RefreshTokenDTO refreshTokenDTO){
        return convertToDTO(refreshTokenRepository.save(convertToEntity(refreshTokenDTO)));

    }

    public void delete(final RefreshTokenDTO refreshTokenDTO){
        refreshTokenRepository.delete(convertToEntity(refreshTokenDTO));

    }

    public void deleteByUser(final Integer userId){
        refreshTokenRepository.deleteByUser(userRepository.getById(userId));

    }


}
