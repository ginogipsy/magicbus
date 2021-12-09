package com.ginogipsy.magicbus.marshall;

import com.ginogipsy.magicbus.domain.MagicbusLocation;
import com.ginogipsy.magicbus.dto.PosizioneMagicBusDTO;
import com.ginogipsy.magicbus.repository.MagicbusLocationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PosizioneMagicBusMapper {

    private final ModelMapper modelMapper;
    private final MagicbusLocationRepository magicbusLocationRepository;

    public PosizioneMagicBusMapper(ModelMapper modelMapper, MagicbusLocationRepository magicbusLocationRepository) {
        this.modelMapper = modelMapper;
        this.magicbusLocationRepository = magicbusLocationRepository;
    }

    public MagicbusLocation convertToEntity(PosizioneMagicBusDTO posizioneMagicBusDTO){
        return (posizioneMagicBusDTO != null) ? modelMapper.map(posizioneMagicBusDTO, MagicbusLocation.class) : null;
    }

    public PosizioneMagicBusDTO convertToDTO(MagicbusLocation magicbusLocation){
        return (magicbusLocation != null) ? modelMapper.map(magicbusLocation, PosizioneMagicBusDTO.class) : null;
    }
}
