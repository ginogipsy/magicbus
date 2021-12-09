package com.ginogipsy.magicbus.marshall;

import com.ginogipsy.magicbus.domain.FriedIngredient;
import com.ginogipsy.magicbus.dto.FriedIngredientDTO;
import com.ginogipsy.magicbus.repository.FriedIngredientRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class FriedIngredientMapper {

    private final ModelMapper modelMapper;
    private final FriedIngredientRepository friedIngredientRepository;

    public FriedIngredientMapper(ModelMapper modelMapper, FriedIngredientRepository friedIngredientRepository) {
        this.modelMapper = modelMapper;
        this.friedIngredientRepository = friedIngredientRepository;
    }

    public FriedIngredient convertToEntity(FriedIngredientDTO friedIngredientDTO){
        return (friedIngredientDTO != null) ? modelMapper.map(friedIngredientDTO, FriedIngredient.class) : null;
    }

    public FriedIngredientDTO convertToDTO(FriedIngredient friedIngredient){
        return (friedIngredient != null) ? modelMapper.map(friedIngredient, FriedIngredientDTO.class) : null;
    }
}
