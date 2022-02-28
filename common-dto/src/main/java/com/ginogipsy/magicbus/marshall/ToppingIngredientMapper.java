package com.ginogipsy.magicbus.marshall;


import com.ginogipsy.magicbus.domain.ToppingIngredient;
import com.ginogipsy.magicbus.dto.ToppingIngredientDTO;
import com.ginogipsy.magicbus.repository.TasteIngredientRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ToppingIngredientMapper {

    private final ModelMapper modelMapper;
    private final TasteIngredientRepository tasteIngredientRepository;

    public ToppingIngredientMapper(ModelMapper modelMapper, TasteIngredientRepository tasteIngredientRepository) {
        this.modelMapper = modelMapper;
        this.tasteIngredientRepository = tasteIngredientRepository;
    }

    public ToppingIngredient convertToEntity(final ToppingIngredientDTO toppingIngredientDTO){
        return Optional.ofNullable(toppingIngredientDTO)
                .map(t -> modelMapper.map(t, ToppingIngredient.class))
            .orElse(null);
    }

    public ToppingIngredientDTO convertToDTO(final ToppingIngredient toppingIngredient){
        return Optional.ofNullable(toppingIngredient)
                .map(t -> modelMapper.map(t, ToppingIngredientDTO.class))
            .orElse(null);
    }
}
