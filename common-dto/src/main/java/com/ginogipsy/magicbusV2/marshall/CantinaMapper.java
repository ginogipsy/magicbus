package com.ginogipsy.magicbusV2.marshall;


import com.ginogipsy.magicbusV2.domain.Cantina;
import com.ginogipsy.magicbusV2.dto.CantinaDTO;
import com.ginogipsy.magicbusV2.repository.CantinaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CantinaMapper {

    private final ModelMapper modelMapper;
    private final CantinaRepository cantinaRepository;

    public CantinaMapper(ModelMapper modelMapper, CantinaRepository cantinaRepository) {
        this.modelMapper = modelMapper;
        this.cantinaRepository = cantinaRepository;
    }

    public Cantina convertToEntity(CantinaDTO cantinaDTO){
        return (cantinaDTO != null) ? modelMapper.map(cantinaDTO, Cantina.class) : null;
    }

    public CantinaDTO convertToDTO(Cantina cantina){
        return (cantina != null) ? modelMapper.map(cantina, CantinaDTO.class) : null;
    }
}
