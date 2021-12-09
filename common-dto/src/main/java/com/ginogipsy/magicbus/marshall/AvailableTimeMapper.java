package com.ginogipsy.magicbus.marshall;


import com.ginogipsy.magicbus.domain.AvailableTime;
import com.ginogipsy.magicbus.dto.AvailableTimeDTO;
import com.ginogipsy.magicbus.repository.AvailableTimeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class AvailableTimeMapper {

    private final ModelMapper modelMapper;
    private final AvailableTimeRepository availableTimeRepository;

    public AvailableTimeMapper(ModelMapper modelMapper, AvailableTimeRepository availableTimeRepository) {
        this.modelMapper = modelMapper;
        this.availableTimeRepository = availableTimeRepository;
    }

    public AvailableTime convertToEntity(AvailableTimeDTO availableTimeDTO){
        return (availableTimeDTO != null) ? modelMapper.map(availableTimeDTO, AvailableTime.class) : null;
    }

    public AvailableTimeDTO convertToDTO(AvailableTime availableTime){
        return (availableTime != null) ? modelMapper.map(availableTime, AvailableTimeDTO.class) : null;
    }
}
