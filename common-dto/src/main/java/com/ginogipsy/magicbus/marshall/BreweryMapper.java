package com.ginogipsy.magicbus.marshall;


import com.ginogipsy.magicbus.domain.Brewery;
import com.ginogipsy.magicbus.dto.BreweryDTO;
import com.ginogipsy.magicbus.repository.BreweryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class BreweryMapper {

    private final ModelMapper modelMapper;
    private final BreweryRepository breweryRepository;

    public BreweryMapper(ModelMapper modelMapper, BreweryRepository breweryRepository) {
        this.modelMapper = modelMapper;
        this.breweryRepository = breweryRepository;
    }

    public Brewery convertToEntity(final BreweryDTO breweryDTO){
        return Optional.ofNullable(breweryDTO)
                .map(b -> modelMapper.map(b, Brewery.class))
            .orElse(null);
    }

    public BreweryDTO convertToDTO(final Brewery brewery){
        return Optional.ofNullable(brewery)
                .map(b -> modelMapper.map(b, BreweryDTO.class))
            .orElse(null);
    }
}
