package com.ginogipsy.magicbus.marshall;


import com.ginogipsy.magicbus.domain.Ingredient;

import com.ginogipsy.magicbus.dto.IngredienteDTO;
import com.ginogipsy.magicbus.repository.IngredienteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class IngredienteMapper {

    private final ModelMapper modelMapper;
    private final IngredienteRepository ingredienteRepository;

    public IngredienteMapper(ModelMapper modelMapper, IngredienteRepository ingredienteRepository) {
        this.modelMapper = modelMapper;
        this.ingredienteRepository = ingredienteRepository;
    }

    public Ingredient convertToEntity(IngredienteDTO ingredienteDTO){
        return (ingredienteDTO != null) ? modelMapper.map(ingredienteDTO, Ingredient.class) : null;
    }

    public IngredienteDTO convertToDTO(Ingredient ingredient){
        return (ingredient != null) ? modelMapper.map(ingredient, IngredienteDTO.class) : null;
    }
}
