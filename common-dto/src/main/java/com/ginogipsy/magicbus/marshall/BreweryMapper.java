package com.ginogipsy.magicbus.marshall;


import com.ginogipsy.magicbus.domain.Brewery;
import com.ginogipsy.magicbus.dto.BreweryDTO;
import com.ginogipsy.magicbus.repository.BreweryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class BreweryMapper {

    private final ModelMapper modelMapper;
    private final BreweryRepository breweryRepository;

    public BreweryMapper(ModelMapper modelMapper, BreweryRepository breweryRepository) {
        this.modelMapper = modelMapper;
        this.breweryRepository = breweryRepository;
    }

    public Brewery convertToEntity(BreweryDTO breweryDTO){
        return (breweryDTO != null) ? modelMapper.map(breweryDTO, Brewery.class) : null;
    }

    public BreweryDTO convertToDTO(Brewery brewery){
        return (brewery != null) ? modelMapper.map(brewery, BreweryDTO.class) : null;
    }
}
