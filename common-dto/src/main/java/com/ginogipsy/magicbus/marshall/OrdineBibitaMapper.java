package com.ginogipsy.magicbus.marshall;

import com.ginogipsy.magicbus.domain.DrinkOrder;
import com.ginogipsy.magicbus.dto.OrdineBibitaDTO;
import com.ginogipsy.magicbus.repository.OrdineBibitaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class OrdineBibitaMapper {

    private final ModelMapper modelMapper;
    private final OrdineBibitaRepository ordineBibitaRepository;

    public OrdineBibitaMapper(ModelMapper modelMapper, OrdineBibitaRepository ordineBibitaRepository) {
        this.modelMapper = modelMapper;
        this.ordineBibitaRepository = ordineBibitaRepository;
    }

    public DrinkOrder convertToEntity(OrdineBibitaDTO ordineBibitaDTO){
        return (ordineBibitaDTO != null) ? modelMapper.map(ordineBibitaDTO, DrinkOrder.class) : null;
    }

    public OrdineBibitaDTO convertToDTO(DrinkOrder drinkOrder){
        return (drinkOrder != null) ? modelMapper.map(drinkOrder, OrdineBibitaDTO.class) : null;
    }
}

