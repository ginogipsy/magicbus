package com.ginogipsy.magicbus.marshall;

import com.ginogipsy.magicbus.domain.MagicbusLocation;
import com.ginogipsy.magicbus.dto.PosizioneMagicBusDTO;
import com.ginogipsy.magicbus.repository.PosizioneMagicBusRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PosizioneMagicBusMapper {

    private final ModelMapper modelMapper;
    private final PosizioneMagicBusRepository posizioneMagicBusRepository;

    public PosizioneMagicBusMapper(ModelMapper modelMapper, PosizioneMagicBusRepository posizioneMagicBusRepository) {
        this.modelMapper = modelMapper;
        this.posizioneMagicBusRepository = posizioneMagicBusRepository;
    }

    public MagicbusLocation convertToEntity(PosizioneMagicBusDTO posizioneMagicBusDTO){
        return (posizioneMagicBusDTO != null) ? modelMapper.map(posizioneMagicBusDTO, MagicbusLocation.class) : null;
    }

    public PosizioneMagicBusDTO convertToDTO(MagicbusLocation magicbusLocation){
        return (magicbusLocation != null) ? modelMapper.map(magicbusLocation, PosizioneMagicBusDTO.class) : null;
    }
}
