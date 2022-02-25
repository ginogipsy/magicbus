package com.ginogipsy.magicbus.marshall;


import com.ginogipsy.magicbus.domain.AvailableTime;
import com.ginogipsy.magicbus.dto.AvailableTimeDTO;
import com.ginogipsy.magicbus.repository.AvailableTimeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AvailableTimeMapper {

    private final ModelMapper modelMapper;
    private final AvailableTimeRepository availableTimeRepository;

    public AvailableTimeMapper(ModelMapper modelMapper, AvailableTimeRepository availableTimeRepository) {
        this.modelMapper = modelMapper;
        this.availableTimeRepository = availableTimeRepository;
    }

    public AvailableTime convertToEntity(final AvailableTimeDTO availableTimeDTO){
        return Optional.ofNullable(availableTimeDTO)
                .map(at -> modelMapper.map(at, AvailableTime.class))
                .orElse(null);
    }

    public AvailableTimeDTO convertToDTO(final AvailableTime availableTime){
        return Optional.ofNullable(availableTime)
                .map(at -> modelMapper.map(at, AvailableTimeDTO.class))
                .orElse(null);
    }
}
