package com.ginogipsy.magicbus.marshall;

import com.ginogipsy.magicbus.domain.TasteOrder;
import com.ginogipsy.magicbus.dto.OrdineGustoDTO;
import com.ginogipsy.magicbus.repository.TasteOrderRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class OrdineGustoMapper {

    private final ModelMapper modelMapper;
    private final TasteOrderRepository tasteOrderRepository;

    public OrdineGustoMapper(ModelMapper modelMapper, TasteOrderRepository tasteOrderRepository) {
        this.modelMapper = modelMapper;
        this.tasteOrderRepository = tasteOrderRepository;
    }

    public TasteOrder convertToEntity(OrdineGustoDTO ordineGustoDTO){
        return (ordineGustoDTO != null) ? modelMapper.map(ordineGustoDTO, TasteOrder.class) : null;
    }

    public OrdineGustoDTO convertToDTO(TasteOrder tasteOrder){
        return (tasteOrder != null) ? modelMapper.map(tasteOrder, OrdineGustoDTO.class) : null;
    }
}
