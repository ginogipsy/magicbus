package com.ginogipsy.magicbus.marshall;

import com.ginogipsy.magicbus.domain.DrinkOrder;
import com.ginogipsy.magicbus.dto.DrinkOrderDTO;
import com.ginogipsy.magicbus.repository.DrinkOrderRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class DrinkOrderMapper {

    private final ModelMapper modelMapper;
    private final DrinkOrderRepository drinkOrderRepository;

    public DrinkOrderMapper(ModelMapper modelMapper, DrinkOrderRepository drinkOrderRepository) {
        this.modelMapper = modelMapper;
        this.drinkOrderRepository = drinkOrderRepository;
    }

    public DrinkOrder convertToEntity(DrinkOrderDTO drinkOrderDTO){
        return (drinkOrderDTO != null) ? modelMapper.map(drinkOrderDTO, DrinkOrder.class) : null;
    }

    public DrinkOrderDTO convertToDTO(DrinkOrder drinkOrder){
        return (drinkOrder != null) ? modelMapper.map(drinkOrder, DrinkOrderDTO.class) : null;
    }
}

