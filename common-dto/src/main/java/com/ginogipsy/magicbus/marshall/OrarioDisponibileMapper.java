package com.ginogipsy.magicbus.marshall;


import com.ginogipsy.magicbus.domain.OrarioDisponibile;
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

    public OrarioDisponibile convertToEntity(OrarioDisponibileDTO orarioDisponibileDTO){
        return (orarioDisponibileDTO != null) ? modelMapper.map(orarioDisponibileDTO, OrarioDisponibile.class) : null;
    }

    public OrarioDisponibileDTO convertToDTO(OrarioDisponibile orarioDisponibile){
        return (orarioDisponibile != null) ? modelMapper.map(orarioDisponibile, OrarioDisponibileDTO.class) : null;
    }
}
