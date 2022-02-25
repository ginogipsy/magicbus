package com.ginogipsy.magicbus.marshall;


import com.ginogipsy.magicbus.domain.Ingredient;

import com.ginogipsy.magicbus.dto.IngredientDTO;
import com.ginogipsy.magicbus.repository.IngredientRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class IngredientMapper {

    private final ModelMapper modelMapper;
    private final IngredientRepository ingredientRepository;

    public IngredientMapper(ModelMapper modelMapper, IngredientRepository ingredientRepository) {
        this.modelMapper = modelMapper;
        this.ingredientRepository = ingredientRepository;
    }

    public Ingredient convertToEntity(final IngredientDTO ingredientDTO){
        return Optional.ofNullable(ingredientDTO)
                .map(i -> modelMapper.map(i, Ingredient.class))
            .orElse(null);
    }

    public IngredientDTO convertToDTO(final Ingredient ingredient){
        return Optional.ofNullable(ingredient)
                .map(i -> modelMapper.map(i, IngredientDTO.class))
            .orElse(null);
    }
}
