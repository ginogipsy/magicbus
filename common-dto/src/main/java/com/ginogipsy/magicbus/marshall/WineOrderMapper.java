package com.ginogipsy.magicbus.marshall;


import com.ginogipsy.magicbus.domain.WineOrder;
import com.ginogipsy.magicbus.dto.WineOrderDTO;
import com.ginogipsy.magicbus.repository.WineOrderRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class WineOrderMapper {

    private final ModelMapper modelMapper;
    private final WineOrderRepository wineOrderRepository;

    public WineOrderMapper(ModelMapper modelMapper, WineOrderRepository wineOrderRepository) {
        this.modelMapper = modelMapper;
        this.wineOrderRepository = wineOrderRepository;
    }

    public WineOrder convertToEntity(final WineOrderDTO wineOrderDTO){
        return Optional.ofNullable(wineOrderDTO)
                .map(wo -> modelMapper.map(wo, WineOrder.class))
            .orElse(null);
    }

    public WineOrderDTO convertToDTO(final WineOrder wineOrder){
        return Optional.ofNullable(wineOrder)
                .map(wo -> modelMapper.map(wo, WineOrderDTO.class))
            .orElse(null);
    }
}
