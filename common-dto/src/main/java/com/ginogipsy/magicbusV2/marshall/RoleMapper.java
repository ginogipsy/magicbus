package com.ginogipsy.magicbusV2.marshall;

import com.ginogipsy.magicbusV2.domain.Profilo;
import com.ginogipsy.magicbusV2.domain.Role;
import com.ginogipsy.magicbusV2.dto.RoleDTO;
import com.ginogipsy.magicbusV2.repository.RoleRepository;
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
