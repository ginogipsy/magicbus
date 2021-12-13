package com.ginogipsy.magicbus.marshall;

import com.ginogipsy.magicbus.domain.ToppingOrder;
import com.ginogipsy.magicbus.dto.ToppingOrderDTO;
import com.ginogipsy.magicbus.repository.TasteOrderRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ToppingOrderMapper {

    private final ModelMapper modelMapper;
    private final TasteOrderRepository tasteOrderRepository;

    public ToppingOrderMapper(ModelMapper modelMapper, TasteOrderRepository tasteOrderRepository) {
        this.modelMapper = modelMapper;
        this.tasteOrderRepository = tasteOrderRepository;
    }

    public ToppingOrder convertToEntity(ToppingOrderDTO toppingOrderDTO){
        return (toppingOrderDTO != null) ? modelMapper.map(toppingOrderDTO, ToppingOrder.class) : null;
    }

    public ToppingOrderDTO convertToDTO(ToppingOrder toppingOrder){
        return (toppingOrder != null) ? modelMapper.map(toppingOrder, ToppingOrderDTO.class) : null;
    }
}
