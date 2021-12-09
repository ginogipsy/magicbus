package com.ginogipsy.magicbus.marshall;


import com.ginogipsy.magicbus.domain.Brewery;
import com.ginogipsy.magicbus.dto.BirrificioDTO;
import com.ginogipsy.magicbus.repository.BreweryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class BirrificioMapper {

    private final ModelMapper modelMapper;
    private final BreweryRepository breweryRepository;

    public BirrificioMapper(ModelMapper modelMapper, BreweryRepository breweryRepository) {
        this.modelMapper = modelMapper;
        this.breweryRepository = breweryRepository;
    }

    public Brewery convertToEntity(BirrificioDTO birrificioDTO){
        return (birrificioDTO != null) ? modelMapper.map(birrificioDTO, Brewery.class) : null;
    }

    public BirrificioDTO convertToDTO(Brewery brewery){
        return (brewery != null) ? modelMapper.map(brewery, BirrificioDTO.class) : null;
    }
}
