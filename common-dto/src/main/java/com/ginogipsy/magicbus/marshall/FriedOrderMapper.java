package com.ginogipsy.magicbus.marshall;

import com.ginogipsy.magicbus.domain.FriedOrder;
import com.ginogipsy.magicbus.dto.FriedOrderDTO;
import com.ginogipsy.magicbus.repository.FriedOrderRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class FriedOrderMapper {

    private final ModelMapper modelMapper;
    private final FriedOrderRepository friedOrderRepository;

    public FriedOrderMapper(ModelMapper modelMapper, FriedOrderRepository friedOrderRepository) {
        this.modelMapper = modelMapper;
        this.friedOrderRepository = friedOrderRepository;
    }

    public FriedOrder convertToEntity(final FriedOrderDTO friedOrderDTO){
        return Optional.ofNullable(friedOrderDTO)
                .map(fo -> modelMapper.map(fo, FriedOrder.class))
            .orElse(null);
    }

    public FriedOrderDTO convertToDTO(final FriedOrder friedOrder){
        return Optional.ofNullable(friedOrder)
                .map(fo -> modelMapper.map(fo, FriedOrderDTO.class))
            .orElse(null);
    }
}
