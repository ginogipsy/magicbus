package com.ginogipsy.magicbus.marshall;


import com.ginogipsy.magicbus.domain.Vino;
import com.ginogipsy.magicbus.dto.VinoDTO;
import com.ginogipsy.magicbus.repository.VinoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class VinoMapper {

    private final ModelMapper modelMapper;
    private final VinoRepository vinoRepository;

    public VinoMapper(ModelMapper modelMapper, VinoRepository vinoRepository) {
        this.modelMapper = modelMapper;
        this.vinoRepository = vinoRepository;
    }

    public Vino convertToEntity(VinoDTO vinoDTO){
        return (vinoDTO != null) ? modelMapper.map(vinoDTO, Vino.class) : null;
    }

    public VinoDTO convertToDTO(Vino vino){
        return (vino != null) ? modelMapper.map(vino, VinoDTO.class) : null;
    }
}
