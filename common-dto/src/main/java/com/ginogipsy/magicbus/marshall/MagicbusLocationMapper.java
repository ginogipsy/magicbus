package com.ginogipsy.magicbus.marshall;

import com.ginogipsy.magicbus.domain.MagicbusLocation;
import com.ginogipsy.magicbus.dto.MagicbusLocationDTO;
import com.ginogipsy.magicbus.repository.MagicbusLocationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MagicbusLocationMapper {

    private final ModelMapper modelMapper;
    private final MagicbusLocationRepository magicbusLocationRepository;

    public MagicbusLocationMapper(ModelMapper modelMapper, MagicbusLocationRepository magicbusLocationRepository) {
        this.modelMapper = modelMapper;
        this.magicbusLocationRepository = magicbusLocationRepository;
    }

    public MagicbusLocation convertToEntity(final MagicbusLocationDTO magicbusLocationDTO){
        return Optional.ofNullable(magicbusLocationDTO)
                .map(ml -> modelMapper.map(ml, MagicbusLocation.class))
            .orElse(null);
    }

    public MagicbusLocationDTO convertToDTO(MagicbusLocation magicbusLocation){
        return Optional.ofNullable(magicbusLocation)
                .map(ml -> modelMapper.map(ml, MagicbusLocationDTO.class))
            .orElse(null);
    }
}
