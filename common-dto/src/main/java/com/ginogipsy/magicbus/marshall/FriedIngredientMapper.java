package com.ginogipsy.magicbus.marshall;

import com.ginogipsy.magicbus.domain.FriedIngredient;
import com.ginogipsy.magicbus.dto.FriedIngredientDTO;
import com.ginogipsy.magicbus.repository.FriedIngredientRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class FriedIngredientMapper {

    private final ModelMapper modelMapper;
    private final FriedIngredientRepository friedIngredientRepository;

    public FriedIngredientMapper(ModelMapper modelMapper, FriedIngredientRepository friedIngredientRepository) {
        this.modelMapper = modelMapper;
        this.friedIngredientRepository = friedIngredientRepository;
    }

    public FriedIngredient convertToEntity(final FriedIngredientDTO friedIngredientDTO){
        return Optional.ofNullable(friedIngredientDTO)
                .map(fi -> modelMapper.map(fi, FriedIngredient.class))
            .orElse(null);
    }

    public FriedIngredientDTO convertToDTO(final FriedIngredient friedIngredient){
        return Optional.ofNullable(friedIngredient)
                .map(fi -> modelMapper.map(fi, FriedIngredientDTO.class))
            .orElse(null);
    }
}
