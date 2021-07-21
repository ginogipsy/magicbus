package com.ginogipsy.magicbusV2.marshall;

import com.ginogipsy.magicbusV2.domain.Bibita;
import com.ginogipsy.magicbusV2.domain.Fritto;
import com.ginogipsy.magicbusV2.dto.BibitaDTO;
import com.ginogipsy.magicbusV2.dto.FrittoDTO;
import com.ginogipsy.magicbusV2.repository.FrittoRepository;
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
