package com.ginogipsy.magicbusV2.marshall;

import com.ginogipsy.magicbusV2.domain.OrdineBibita;
import com.ginogipsy.magicbusV2.dto.OrdineBibitaDTO;
import com.ginogipsy.magicbusV2.repository.OrdineBibitaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class OrdineBibitaMapper {

    private final ModelMapper modelMapper;
    private final OrdineBibitaRepository ordineBibitaRepository;

    public OrdineBibitaMapper(ModelMapper modelMapper, OrdineBibitaRepository ordineBibitaRepository) {
        this.modelMapper = modelMapper;
        this.ordineBibitaRepository = ordineBibitaRepository;
    }

    public OrdineBibita convertToEntity(OrdineBibitaDTO ordineBibitaDTO){
        return (ordineBibitaDTO != null) ? modelMapper.map(ordineBibitaDTO, OrdineBibita.class) : null;
    }

    public OrdineBibitaDTO convertToDTO(OrdineBibita ordineBibita){
        return (ordineBibita != null) ? modelMapper.map(ordineBibita, OrdineBibitaDTO.class) : null;
    }
}

