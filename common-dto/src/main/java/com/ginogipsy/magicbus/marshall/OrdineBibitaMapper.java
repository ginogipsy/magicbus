package com.ginogipsy.magicbus.marshall;

import com.ginogipsy.magicbus.domain.DrinkOrder;
import com.ginogipsy.magicbus.dto.OrdineBibitaDTO;
import com.ginogipsy.magicbus.repository.DrinkOrderRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class OrdineBibitaMapper {

    private final ModelMapper modelMapper;
    private final DrinkOrderRepository drinkOrderRepository;

    public OrdineBibitaMapper(ModelMapper modelMapper, DrinkOrderRepository drinkOrderRepository) {
        this.modelMapper = modelMapper;
        this.drinkOrderRepository = drinkOrderRepository;
    }

    public DrinkOrder convertToEntity(OrdineBibitaDTO ordineBibitaDTO){
        return (ordineBibitaDTO != null) ? modelMapper.map(ordineBibitaDTO, DrinkOrder.class) : null;
    }

    public OrdineBibitaDTO convertToDTO(DrinkOrder drinkOrder){
        return (drinkOrder != null) ? modelMapper.map(drinkOrder, OrdineBibitaDTO.class) : null;
    }
}

