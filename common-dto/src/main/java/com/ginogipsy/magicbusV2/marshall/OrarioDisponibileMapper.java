package com.ginogipsy.magicbusV2.marshall;


import com.ginogipsy.magicbusV2.domain.OrarioDisponibile;
import com.ginogipsy.magicbusV2.dto.OrarioDisponibileDTO;
import com.ginogipsy.magicbusV2.repository.OrarioDisponibileRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class OrarioDisponibileMapper {

    private final ModelMapper modelMapper;
    private final OrarioDisponibileRepository orarioDisponibileRepository;

    public OrarioDisponibileMapper(ModelMapper modelMapper, OrarioDisponibileRepository orarioDisponibileRepository) {
        this.modelMapper = modelMapper;
        this.orarioDisponibileRepository = orarioDisponibileRepository;
    }

    public OrarioDisponibile convertToEntity(OrarioDisponibileDTO orarioDisponibileDTO){
        return (orarioDisponibileDTO != null) ? modelMapper.map(orarioDisponibileDTO, OrarioDisponibile.class) : null;
    }

    public OrarioDisponibileDTO convertToDTO(OrarioDisponibile orarioDisponibile){
        return (orarioDisponibile != null) ? modelMapper.map(orarioDisponibile, OrarioDisponibileDTO.class) : null;
    }
}
