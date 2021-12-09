package com.ginogipsy.magicbus.marshall;


import com.ginogipsy.magicbus.domain.AvailableTime;
import com.ginogipsy.magicbus.dto.OrarioDisponibileDTO;
import com.ginogipsy.magicbus.repository.AvailableTimeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class OrarioDisponibileMapper {

    private final ModelMapper modelMapper;
    private final AvailableTimeRepository availableTimeRepository;

    public OrarioDisponibileMapper(ModelMapper modelMapper, AvailableTimeRepository availableTimeRepository) {
        this.modelMapper = modelMapper;
        this.availableTimeRepository = availableTimeRepository;
    }

    public AvailableTime convertToEntity(OrarioDisponibileDTO orarioDisponibileDTO){
        return (orarioDisponibileDTO != null) ? modelMapper.map(orarioDisponibileDTO, AvailableTime.class) : null;
    }

    public OrarioDisponibileDTO convertToDTO(AvailableTime availableTime){
        return (availableTime != null) ? modelMapper.map(availableTime, OrarioDisponibileDTO.class) : null;
    }
}
