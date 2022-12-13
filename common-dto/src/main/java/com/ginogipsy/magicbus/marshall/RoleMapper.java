package com.ginogipsy.magicbus.marshall;

import com.ginogipsy.magicbus.domain.enums.ProfileEnum;
import com.ginogipsy.magicbus.domain.Role;
import com.ginogipsy.magicbus.dto.RoleDTO;
import com.ginogipsy.magicbus.repository.RoleRepository;
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
public class RoleMapper {

    private final ModelMapper modelMapper;
    private final RoleRepository roleRepository;

    public Optional<RoleDTO> findByProfile(final String profile){
        log.info("RoleMapper - findByProfile() -> Searching role where profile is {}..", profile);
        return Optional.ofNullable(profile)
                .flatMap(p -> roleRepository.findByProfileEnum(ProfileEnum.getProfile(p)))
                .map(this::convertToDTO);
    }

    public Role convertToDTO(final RoleDTO roleDTO) {
        return Optional.ofNullable(roleDTO)
                .map(r -> modelMapper.map(r, Role.class))
                .orElse(null);
    }

    public Optional<Role> convertToEntity(final Optional<RoleDTO> roleDTO) {
        return Optional.ofNullable(roleDTO)
                .map(r -> modelMapper.map(r, Role.class));
    }

    public RoleDTO convertToDTO(final Role role){
        return Optional.ofNullable(role)
                .map(r -> modelMapper.map(r, RoleDTO.class))
            .orElse(null);
    }

    public Optional<RoleDTO> convertToDTO(final Optional<Role> role){
        return Optional.ofNullable(role)
                .map(r -> modelMapper.map(r, RoleDTO.class));
    }
}
