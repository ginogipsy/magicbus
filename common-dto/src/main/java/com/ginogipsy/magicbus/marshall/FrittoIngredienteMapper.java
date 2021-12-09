package com.ginogipsy.magicbus.marshall;

import com.ginogipsy.magicbus.domain.FriedIngredient;
import com.ginogipsy.magicbus.dto.FrittoIngredienteDTO;
import com.ginogipsy.magicbus.repository.FriedIngredientRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class FrittoIngredienteMapper {

    private final ModelMapper modelMapper;
    private final FriedIngredientRepository friedIngredientRepository;

    public FrittoIngredienteMapper(ModelMapper modelMapper, FriedIngredientRepository friedIngredientRepository) {
        this.modelMapper = modelMapper;
        this.friedIngredientRepository = friedIngredientRepository;
    }

    public FriedIngredient convertToEntity(FrittoIngredienteDTO frittoIngredienteDTO){
        return (frittoIngredienteDTO != null) ? modelMapper.map(frittoIngredienteDTO, FriedIngredient.class) : null;
    }

    public FrittoIngredienteDTO convertToDTO(FriedIngredient friedIngredient){
        return (friedIngredient != null) ? modelMapper.map(friedIngredient, FrittoIngredienteDTO.class) : null;
    }
}
