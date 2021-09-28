package com.ginogipsy.magicbus.marshall;

import com.ginogipsy.magicbus.domain.OrdineFritto;
import com.ginogipsy.magicbus.dto.OrdineFrittoDTO;
import com.ginogipsy.magicbus.repository.OrdineFrittoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class OrdineFrittoMapper {

    private final ModelMapper modelMapper;
    private final OrdineFrittoRepository ordineFrittoRepository;

    public OrdineFrittoMapper(ModelMapper modelMapper, OrdineFrittoRepository ordineFrittoRepository) {
        this.modelMapper = modelMapper;
        this.ordineFrittoRepository = ordineFrittoRepository;
    }

    public OrdineFritto convertToEntity(OrdineFrittoDTO ordineFrittoDTO){
        return (ordineFrittoDTO != null) ? modelMapper.map(ordineFrittoDTO, OrdineFritto.class) : null;
    }

    public OrdineFrittoDTO convertToDTO(OrdineFritto ordineFritto){
        return (ordineFritto != null) ? modelMapper.map(ordineFritto, OrdineFrittoDTO.class) : null;
    }
}
