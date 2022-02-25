package com.ginogipsy.magicbus.marshall;

import com.ginogipsy.magicbus.domain.enums.Profile;
import com.ginogipsy.magicbus.domain.Role;
import com.ginogipsy.magicbus.dto.RoleDTO;
import com.ginogipsy.magicbus.repository.RoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
public class RoleMapper {

    private final ModelMapper modelMapper;
    private final RoleRepository roleRepository;

    public RoleMapper(ModelMapper modelMapper, RoleRepository roleRepository) {
        this.modelMapper = modelMapper;
        this.roleRepository = roleRepository;
    }

    public RoleDTO findByProfile(final String profile){
        log.info("Searching role where profile is " + profile +"..");
        return Optional.ofNullable(profile)
                .map(p -> roleRepository.findByProfile(Profile.getProfile(p)))
                .map(this::convertToDTO)
                .orElse(null);
    }

    public RoleDTO convertToDTO(final Role role){
        return Optional.ofNullable(role)
                .map(r -> modelMapper.map(r, RoleDTO.class))
            .orElse(null);
    }
}
