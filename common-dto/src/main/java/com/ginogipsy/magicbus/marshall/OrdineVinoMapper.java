package com.ginogipsy.magicbus.marshall;


import com.ginogipsy.magicbus.domain.WineOrder;
import com.ginogipsy.magicbus.dto.OrdineVinoDTO;
import com.ginogipsy.magicbus.repository.OrdineVinoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class OrdineVinoMapper {

    private final ModelMapper modelMapper;
    private final OrdineVinoRepository ordineVinoRepository;

    public OrdineVinoMapper(ModelMapper modelMapper, OrdineVinoRepository ordineVinoRepository) {
        this.modelMapper = modelMapper;
        this.ordineVinoRepository = ordineVinoRepository;
    }

    public WineOrder convertToEntity(OrdineVinoDTO ordineVinoDTO){
        return (ordineVinoDTO != null) ? modelMapper.map(ordineVinoDTO, WineOrder.class) : null;
    }

    public OrdineVinoDTO convertToDTO(WineOrder wineOrder){
        return (wineOrder != null) ? modelMapper.map(wineOrder, OrdineVinoDTO.class) : null;
    }
}
