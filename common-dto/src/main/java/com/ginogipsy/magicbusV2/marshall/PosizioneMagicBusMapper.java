package com.ginogipsy.magicbusV2.marshall;

import com.ginogipsy.magicbusV2.domain.PosizioneMagicBus;
import com.ginogipsy.magicbusV2.dto.PosizioneMagicBusDTO;
import com.ginogipsy.magicbusV2.repository.PosizioneMagicBusRepository;
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

    public PosizioneMagicBus convertToEntity(PosizioneMagicBusDTO posizioneMagicBusDTO){
        return (posizioneMagicBusDTO != null) ? modelMapper.map(posizioneMagicBusDTO, PosizioneMagicBus.class) : null;
    }

    public PosizioneMagicBusDTO convertToDTO(PosizioneMagicBus posizioneMagicBus){
        return (posizioneMagicBus != null) ? modelMapper.map(posizioneMagicBus, PosizioneMagicBusDTO.class) : null;
    }
}
