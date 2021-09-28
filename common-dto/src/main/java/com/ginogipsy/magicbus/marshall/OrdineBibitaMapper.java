package com.ginogipsy.magicbus.marshall;

import com.ginogipsy.magicbus.domain.OrdineBibita;
import com.ginogipsy.magicbus.dto.OrdineBibitaDTO;
import com.ginogipsy.magicbus.repository.OrdineBibitaRepository;
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

