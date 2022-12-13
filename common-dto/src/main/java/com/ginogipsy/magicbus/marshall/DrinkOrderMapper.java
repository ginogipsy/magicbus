package com.ginogipsy.magicbus.marshall;

import com.ginogipsy.magicbus.domain.DrinkOrder;
import com.ginogipsy.magicbus.dto.DrinkOrderDTO;
import com.ginogipsy.magicbus.repository.DrinkOrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author ginogipsy
 */

@Slf4j
@Component
@RequiredArgsConstructor
public class DrinkOrderMapper {

    private final ModelMapper modelMapper;
    private final DrinkOrderRepository drinkOrderRepository;

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
    public Optional<DrinkOrder> convertToEntity(final Optional<DrinkOrderDTO> drinkOrderDTO){
        return Optional.ofNullable(drinkOrderDTO)
                .map(dor -> modelMapper.map(dor, DrinkOrder.class));
    }

    public Optional<DrinkOrderDTO> convertToDTO(final Optional<DrinkOrder> drinkOrder){
        return Optional.ofNullable(drinkOrder)
                .map(dr -> modelMapper.map(dr, DrinkOrderDTO.class));
    }
}

