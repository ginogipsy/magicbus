package com.ginogipsy.magicbus.marshall;

import com.ginogipsy.magicbus.domain.Profilo;
import com.ginogipsy.magicbus.domain.Role;
import com.ginogipsy.magicbus.dto.RoleDTO;
import com.ginogipsy.magicbus.repository.RoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper {

    private final ModelMapper modelMapper;
    private final RoleRepository roleRepository;

    public RoleMapper(ModelMapper modelMapper, RoleRepository roleRepository) {
        this.modelMapper = modelMapper;
        this.roleRepository = roleRepository;
    }

    public RoleDTO findByProfilo(Profilo profilo){
        return convertToDTO(roleRepository.findByProfilo(profilo));
    }

    public RoleDTO convertToDTO(Role role){
        return (role != null) ? modelMapper.map(role, RoleDTO.class) : null;
    }
}