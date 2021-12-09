package com.ginogipsy.magicbus.marshall;


import com.ginogipsy.magicbus.domain.Ingredient;

import com.ginogipsy.magicbus.dto.IngredientDTO;
import com.ginogipsy.magicbus.repository.IngredientRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class IngredientMapper {

    private final ModelMapper modelMapper;
    private final IngredientRepository ingredientRepository;

    public IngredientMapper(ModelMapper modelMapper, IngredientRepository ingredientRepository) {
        this.modelMapper = modelMapper;
        this.ingredientRepository = ingredientRepository;
    }

    public Ingredient convertToEntity(IngredientDTO ingredientDTO){
        return (ingredientDTO != null) ? modelMapper.map(ingredientDTO, Ingredient.class) : null;
    }

    public IngredientDTO convertToDTO(Ingredient ingredient){
        return (ingredient != null) ? modelMapper.map(ingredient, IngredientDTO.class) : null;
    }
}
