package com.ginogipsy.magicbus.marshall;


import com.ginogipsy.magicbus.domain.Ingredient;

import com.ginogipsy.magicbus.dto.IngredienteDTO;
import com.ginogipsy.magicbus.repository.IngredientRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class IngredienteMapper {

    private final ModelMapper modelMapper;
    private final IngredientRepository ingredientRepository;

    public IngredienteMapper(ModelMapper modelMapper, IngredientRepository ingredientRepository) {
        this.modelMapper = modelMapper;
        this.ingredientRepository = ingredientRepository;
    }

    public Ingredient convertToEntity(IngredienteDTO ingredienteDTO){
        return (ingredienteDTO != null) ? modelMapper.map(ingredienteDTO, Ingredient.class) : null;
    }

    public IngredienteDTO convertToDTO(Ingredient ingredient){
        return (ingredient != null) ? modelMapper.map(ingredient, IngredienteDTO.class) : null;
    }
}
