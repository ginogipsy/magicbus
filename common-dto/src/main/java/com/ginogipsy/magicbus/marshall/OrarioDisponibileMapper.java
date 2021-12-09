package com.ginogipsy.magicbus.marshall;


import com.ginogipsy.magicbus.domain.AvailableTime;
import com.ginogipsy.magicbus.dto.OrarioDisponibileDTO;
import com.ginogipsy.magicbus.repository.OrarioDisponibileRepository;
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

    public AvailableTime convertToEntity(OrarioDisponibileDTO orarioDisponibileDTO){
        return (orarioDisponibileDTO != null) ? modelMapper.map(orarioDisponibileDTO, AvailableTime.class) : null;
    }

    public OrarioDisponibileDTO convertToDTO(AvailableTime availableTime){
        return (availableTime != null) ? modelMapper.map(availableTime, OrarioDisponibileDTO.class) : null;
    }
}
