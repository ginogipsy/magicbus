package com.ginogipsy.magicbus.marshall;

import com.ginogipsy.magicbus.domain.DrinkOrder;
import com.ginogipsy.magicbus.dto.DrinkOrderDTO;
import com.ginogipsy.magicbus.repository.DrinkOrderRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DrinkOrderMapper {

    private final ModelMapper modelMapper;
    private final DrinkOrderRepository drinkOrderRepository;

    public DrinkOrderMapper(ModelMapper modelMapper, DrinkOrderRepository drinkOrderRepository) {
        this.modelMapper = modelMapper;
        this.drinkOrderRepository = drinkOrderRepository;
    }

    public DrinkOrder convertToEntity(final DrinkOrderDTO drinkOrderDTO){
        return Optional.ofNullable(drinkOrderDTO)
                .map(dor -> modelMapper.map(dor, DrinkOrder.class))
            .orElse(null);
    }

    public DrinkOrderDTO convertToDTO(final DrinkOrder drinkOrder){
        return Optional.ofNullable(drinkOrder)
                .map(dr -> modelMapper.map(dr, DrinkOrderDTO.class))
            .orElse(null);
    }
}

