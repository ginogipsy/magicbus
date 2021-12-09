package com.ginogipsy.magicbus.marshall;


import com.ginogipsy.magicbus.domain.BeerOrder;
import com.ginogipsy.magicbus.dto.BeerOrderDTO;
import com.ginogipsy.magicbus.repository.BeerOrderRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class BeerOrderMapper {

    private final ModelMapper modelMapper;
    private final BeerOrderRepository beerOrderRepository;

    public BeerOrderMapper(ModelMapper modelMapper, BeerOrderRepository beerOrderRepository) {
        this.modelMapper = modelMapper;
        this.beerOrderRepository = beerOrderRepository;
    }

    public BeerOrder convertToEntity(BeerOrderDTO beerOrderDTO){
        return (beerOrderDTO != null) ? modelMapper.map(beerOrderDTO, BeerOrder.class) : null;
    }

    public BeerOrderDTO convertToDTO(BeerOrder beerOrder){
        return (beerOrder != null) ? modelMapper.map(beerOrder, BeerOrderDTO.class) : null;
    }
}
