package com.ginogipsy.magicbus.marshall;

import com.ginogipsy.magicbus.domain.Dough;
import com.ginogipsy.magicbus.dto.DoughDTO;
import com.ginogipsy.magicbus.repository.DoughRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DoughMapper {

    private final ModelMapper modelMapper;
    private final DoughRepository doughRepository;

    public DoughMapper(ModelMapper modelMapper, DoughRepository doughRepository) {
        this.modelMapper = modelMapper;
        this.doughRepository = doughRepository;
    }

    public Dough convertToEntity(final DoughDTO doughDTO){
        return Optional.ofNullable(doughDTO)
                .map(d -> modelMapper.map(d, Dough.class))
            .orElse(null);
    }

    public DoughDTO convertToDTO(final Dough dough){
        return Optional.ofNullable(dough)
                .map(d -> modelMapper.map(d, DoughDTO.class))
            .orElse(null);
    }
}
