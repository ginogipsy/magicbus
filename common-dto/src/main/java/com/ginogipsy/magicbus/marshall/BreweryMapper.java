package com.ginogipsy.magicbus.marshall;


import com.ginogipsy.magicbus.domain.Brewery;
import com.ginogipsy.magicbus.dto.BreweryDTO;
import com.ginogipsy.magicbus.repository.BreweryRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author ginogipsy
 */

@Component
@RequiredArgsConstructor
public class BreweryMapper {

    private final ModelMapper modelMapper;
    private final BreweryRepository breweryRepository;

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
 public Optional<Brewery> convertToEntity(final Optional<BreweryDTO> breweryDTO){
        return Optional.ofNullable(breweryDTO)
                .map(b -> modelMapper.map(b, Brewery.class));
    }

    public Optional<BreweryDTO> convertToDTO(final Optional<Brewery> brewery){
        return Optional.ofNullable(brewery)
                .map(b -> modelMapper.map(b, BreweryDTO.class));
    }
}
