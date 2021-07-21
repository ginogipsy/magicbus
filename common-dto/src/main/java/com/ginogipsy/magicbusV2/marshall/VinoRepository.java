package com.ginogipsy.magicbusV2.marshall;


import com.ginogipsy.magicbusV2.domain.Vino;
import com.ginogipsy.magicbusV2.dto.VinoDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class VinoRepository {

    private final ModelMapper modelMapper;
    private final VinoRepository vinoRepository;

    public VinoRepository(ModelMapper modelMapper, VinoRepository vinoRepository) {
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
