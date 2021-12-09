package com.ginogipsy.magicbus.marshall;

import com.ginogipsy.magicbus.domain.FriedOrder;
import com.ginogipsy.magicbus.dto.OrdineFrittoDTO;
import com.ginogipsy.magicbus.repository.FriedOrderRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class OrdineFrittoMapper {

    private final ModelMapper modelMapper;
    private final FriedOrderRepository friedOrderRepository;

    public OrdineFrittoMapper(ModelMapper modelMapper, FriedOrderRepository friedOrderRepository) {
        this.modelMapper = modelMapper;
        this.friedOrderRepository = friedOrderRepository;
    }

    public FriedOrder convertToEntity(OrdineFrittoDTO ordineFrittoDTO){
        return (ordineFrittoDTO != null) ? modelMapper.map(ordineFrittoDTO, FriedOrder.class) : null;
    }

    public OrdineFrittoDTO convertToDTO(FriedOrder friedOrder){
        return (friedOrder != null) ? modelMapper.map(friedOrder, OrdineFrittoDTO.class) : null;
    }
}
