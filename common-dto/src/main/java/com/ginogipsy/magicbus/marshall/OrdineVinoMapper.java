package com.ginogipsy.magicbus.marshall;


import com.ginogipsy.magicbus.domain.OrdineVino;
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

    public OrdineVino convertToEntity(OrdineVinoDTO ordineVinoDTO){
        return (ordineVinoDTO != null) ? modelMapper.map(ordineVinoDTO, OrdineVino.class) : null;
    }

    public OrdineVinoDTO convertToDTO(OrdineVino ordineVino){
        return (ordineVino != null) ? modelMapper.map(ordineVino, OrdineVinoDTO.class) : null;
    }
}
