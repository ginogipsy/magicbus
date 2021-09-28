package com.ginogipsy.magicbus.marshall;

import com.ginogipsy.magicbus.domain.Fritto;
import com.ginogipsy.magicbus.dto.FrittoDTO;
import com.ginogipsy.magicbus.repository.FrittoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class FrittoMapper {

    private final ModelMapper modelMapper;
    private final FrittoRepository frittoRepository;

    public FrittoMapper(ModelMapper modelMapper, FrittoRepository frittoRepository) {
        this.modelMapper = modelMapper;
        this.frittoRepository = frittoRepository;
    }

    public Fritto convertToEntity(FrittoDTO frittoDTO){
        return (frittoDTO != null) ? modelMapper.map(frittoDTO, Fritto.class) : null;
    }

    public FrittoDTO convertToDTO(Fritto fritto){
        return (fritto != null) ? modelMapper.map(fritto, FrittoDTO.class) : null;
    }
}
