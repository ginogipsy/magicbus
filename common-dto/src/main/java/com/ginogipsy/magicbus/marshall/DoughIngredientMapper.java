package com.ginogipsy.magicbus.marshall;

import com.ginogipsy.magicbus.domain.DoughIngredient;
import com.ginogipsy.magicbus.dto.DoughIngredientDTO;
import com.ginogipsy.magicbus.repository.DoughRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DoughIngredientMapper {

    private final ModelMapper modelMapper;
    private final DoughRepository doughRepository;

    public DoughIngredientMapper(ModelMapper modelMapper, DoughRepository doughRepository) {
        this.modelMapper = modelMapper;
        this.doughRepository = doughRepository;
    }

    public DoughIngredient convertToEntity(final DoughIngredientDTO doughIngredientDTO){
        return Optional.ofNullable(doughIngredientDTO)
                .map(di -> modelMapper.map(di, DoughIngredient.class))
            .orElse(null);
    }

    public DoughIngredientDTO convertToDTO(final DoughIngredient doughIngredient){
        return Optional.ofNullable(doughIngredient)
                .map(di -> modelMapper.map(di, DoughIngredientDTO.class))
            .orElse(null);
    }
}
