package com.ginogipsy.magicbus.marshall;

import com.ginogipsy.magicbus.domain.Dough;
import com.ginogipsy.magicbus.dto.DoughDTO;
import com.ginogipsy.magicbus.repository.DoughRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class DoughMapper {

    private final ModelMapper modelMapper;
    private final DoughRepository doughRepository;

    public DoughMapper(ModelMapper modelMapper, DoughRepository doughRepository) {
        this.modelMapper = modelMapper;
        this.doughRepository = doughRepository;
    }

    public Dough convertToEntity(DoughDTO doughDTO){
        return (doughDTO != null) ? modelMapper.map(doughDTO, Dough.class) : null;
    }

    public DoughDTO convertToDTO(Dough dough){
        return (dough != null) ? modelMapper.map(dough, DoughDTO.class) : null;
    }
}
