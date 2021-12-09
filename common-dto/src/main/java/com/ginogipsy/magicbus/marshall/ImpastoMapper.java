package com.ginogipsy.magicbus.marshall;

import com.ginogipsy.magicbus.domain.Dough;
import com.ginogipsy.magicbus.dto.ImpastoDTO;
import com.ginogipsy.magicbus.repository.DoughRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ImpastoMapper {

    private final ModelMapper modelMapper;
    private final DoughRepository doughRepository;

    public ImpastoMapper(ModelMapper modelMapper, DoughRepository doughRepository) {
        this.modelMapper = modelMapper;
        this.doughRepository = doughRepository;
    }

    public Dough convertToEntity(ImpastoDTO impastoDTO){
        return (impastoDTO != null) ? modelMapper.map(impastoDTO, Dough.class) : null;
    }

    public ImpastoDTO convertToDTO(Dough dough){
        return (dough != null) ? modelMapper.map(dough, ImpastoDTO.class) : null;
    }
}
