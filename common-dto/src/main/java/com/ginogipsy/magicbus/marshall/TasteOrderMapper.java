package com.ginogipsy.magicbus.marshall;

import com.ginogipsy.magicbus.domain.TasteOrder;
import com.ginogipsy.magicbus.dto.TasteOrderDTO;
import com.ginogipsy.magicbus.repository.TasteOrderRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class TasteOrderMapper {

    private final ModelMapper modelMapper;
    private final TasteOrderRepository tasteOrderRepository;

    public TasteOrderMapper(ModelMapper modelMapper, TasteOrderRepository tasteOrderRepository) {
        this.modelMapper = modelMapper;
        this.tasteOrderRepository = tasteOrderRepository;
    }

    public TasteOrder convertToEntity(TasteOrderDTO tasteOrderDTO){
        return (tasteOrderDTO != null) ? modelMapper.map(tasteOrderDTO, TasteOrder.class) : null;
    }

    public TasteOrderDTO convertToDTO(TasteOrder tasteOrder){
        return (tasteOrder != null) ? modelMapper.map(tasteOrder, TasteOrderDTO.class) : null;
    }
}
