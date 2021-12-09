package com.ginogipsy.magicbus.marshall;

import com.ginogipsy.magicbus.domain.FriedOrder;
import com.ginogipsy.magicbus.dto.FriedOrderDTO;
import com.ginogipsy.magicbus.repository.FriedOrderRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class FriedOrderMapper {

    private final ModelMapper modelMapper;
    private final FriedOrderRepository friedOrderRepository;

    public FriedOrderMapper(ModelMapper modelMapper, FriedOrderRepository friedOrderRepository) {
        this.modelMapper = modelMapper;
        this.friedOrderRepository = friedOrderRepository;
    }

    public FriedOrder convertToEntity(FriedOrderDTO friedOrderDTO){
        return (friedOrderDTO != null) ? modelMapper.map(friedOrderDTO, FriedOrder.class) : null;
    }

    public FriedOrderDTO convertToDTO(FriedOrder friedOrder){
        return (friedOrder != null) ? modelMapper.map(friedOrder, FriedOrderDTO.class) : null;
    }
}
