package com.ginogipsy.magicbus.marshall;


import com.ginogipsy.magicbus.domain.BeerOrder;
import com.ginogipsy.magicbus.dto.OrdineBirraDTO;
import com.ginogipsy.magicbus.repository.BeerOrderRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class OrdineBirraMapper {

    private final ModelMapper modelMapper;
    private final BeerOrderRepository beerOrderRepository;

    public OrdineBirraMapper(ModelMapper modelMapper, BeerOrderRepository beerOrderRepository) {
        this.modelMapper = modelMapper;
        this.beerOrderRepository = beerOrderRepository;
    }

    public BeerOrder convertToEntity(OrdineBirraDTO ordineBirraDTO){
        return (ordineBirraDTO != null) ? modelMapper.map(ordineBirraDTO, BeerOrder.class) : null;
    }

    public OrdineBirraDTO convertToDTO(BeerOrder beerOrder){
        return (beerOrder != null) ? modelMapper.map(beerOrder, OrdineBirraDTO.class) : null;
    }
}
