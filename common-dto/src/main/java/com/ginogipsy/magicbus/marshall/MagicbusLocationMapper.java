package com.ginogipsy.magicbus.marshall;

import com.ginogipsy.magicbus.domain.MagicbusLocation;
import com.ginogipsy.magicbus.dto.MagicbusLocationDTO;
import com.ginogipsy.magicbus.repository.MagicbusLocationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class MagicbusLocationMapper {

    private final ModelMapper modelMapper;
    private final MagicbusLocationRepository magicbusLocationRepository;

    public MagicbusLocationMapper(ModelMapper modelMapper, MagicbusLocationRepository magicbusLocationRepository) {
        this.modelMapper = modelMapper;
        this.magicbusLocationRepository = magicbusLocationRepository;
    }

    public MagicbusLocation convertToEntity(MagicbusLocationDTO magicbusLocationDTO){
        return (magicbusLocationDTO != null) ? modelMapper.map(magicbusLocationDTO, MagicbusLocation.class) : null;
    }

    public MagicbusLocationDTO convertToDTO(MagicbusLocation magicbusLocation){
        return (magicbusLocation != null) ? modelMapper.map(magicbusLocation, MagicbusLocationDTO.class) : null;
    }
}
