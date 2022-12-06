package com.ginogipsy.magicbus.marshall;


import com.ginogipsy.magicbus.domain.AvailableTime;
import com.ginogipsy.magicbus.dto.AvailableTimeDTO;
import com.ginogipsy.magicbus.repository.AvailableTimeRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author ginogipsy
 */

@Component
@RequiredArgsConstructor
public class AvailableTimeMapper {

    private final ModelMapper modelMapper;
    private final AvailableTimeRepository availableTimeRepository;

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

    public Optional<AvailableTime> convertToEntity(final Optional<AvailableTimeDTO> availableTimeDTO){
        return Optional.ofNullable(availableTimeDTO)
                .map(at -> modelMapper.map(at, AvailableTime.class));
    }

    public Optional<AvailableTimeDTO> convertToDTO(final Optional<AvailableTime> availableTime){
        return Optional.ofNullable(availableTime)
                .map(at -> modelMapper.map(at, AvailableTimeDTO.class));

    }
}
