package com.ginogipsy.magicbus.marshall;


import com.ginogipsy.magicbus.domain.BeerOrder;
import com.ginogipsy.magicbus.dto.BeerOrderDTO;
import com.ginogipsy.magicbus.repository.BeerOrderRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class BeerOrderMapper {

    private final ModelMapper modelMapper;
    private final BeerOrderRepository beerOrderRepository;

    public BeerOrderMapper(ModelMapper modelMapper, BeerOrderRepository beerOrderRepository) {
        this.modelMapper = modelMapper;
        this.beerOrderRepository = beerOrderRepository;
    }

    public BeerOrder convertToEntity(final BeerOrderDTO beerOrderDTO){
        return Optional.ofNullable(beerOrderDTO)
                .map(bo -> modelMapper.map(bo, BeerOrder.class))
            .orElse(null);
    }

    public BeerOrderDTO convertToDTO(final BeerOrder beerOrder){
        return Optional.ofNullable(beerOrder)
                .map(bo -> modelMapper.map(bo, BeerOrderDTO.class))
            .orElse(null);
    }
}
