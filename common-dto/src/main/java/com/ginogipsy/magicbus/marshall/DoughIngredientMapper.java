package com.ginogipsy.magicbus.marshall;

import com.ginogipsy.magicbus.domain.DoughIngredient;
import com.ginogipsy.magicbus.dto.DoughIngredientDTO;
import com.ginogipsy.magicbus.repository.DoughRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class DoughIngredientMapper {

    private final ModelMapper modelMapper;
    private final DoughRepository doughRepository;

    public DoughIngredientMapper(ModelMapper modelMapper, DoughRepository doughRepository) {
        this.modelMapper = modelMapper;
        this.doughRepository = doughRepository;
    }

    public DoughIngredient convertToEntity(DoughIngredientDTO doughIngredientDTO){
        return (doughIngredientDTO != null) ? modelMapper.map(doughIngredientDTO, DoughIngredient.class) : null;
    }

    public DoughIngredientDTO convertToDTO(DoughIngredient doughIngredient){
        return (doughIngredient != null) ? modelMapper.map(doughIngredient, DoughIngredientDTO.class) : null;
    }
}
