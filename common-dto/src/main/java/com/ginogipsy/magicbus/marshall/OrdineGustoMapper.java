package com.ginogipsy.magicbus.marshall;

import com.ginogipsy.magicbus.domain.TasteOrder;
import com.ginogipsy.magicbus.dto.OrdineGustoDTO;
import com.ginogipsy.magicbus.repository.OrdineGustoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class OrdineGustoMapper {

    private final ModelMapper modelMapper;
    private final OrdineGustoRepository ordineGustoRepository;

    public OrdineGustoMapper(ModelMapper modelMapper, OrdineGustoRepository ordineGustoRepository) {
        this.modelMapper = modelMapper;
        this.ordineGustoRepository = ordineGustoRepository;
    }

    public TasteOrder convertToEntity(OrdineGustoDTO ordineGustoDTO){
        return (ordineGustoDTO != null) ? modelMapper.map(ordineGustoDTO, TasteOrder.class) : null;
    }

    public OrdineGustoDTO convertToDTO(TasteOrder tasteOrder){
        return (tasteOrder != null) ? modelMapper.map(tasteOrder, OrdineGustoDTO.class) : null;
    }
}
