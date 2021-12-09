package com.ginogipsy.magicbus.marshall;

import com.ginogipsy.magicbus.domain.FriedIngredient;
import com.ginogipsy.magicbus.dto.FrittoIngredienteDTO;
import com.ginogipsy.magicbus.repository.FrittoIngredienteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class FrittoIngredienteMapper {

    private final ModelMapper modelMapper;
    private final FrittoIngredienteRepository frittoIngredienteRepository;

    public FrittoIngredienteMapper(ModelMapper modelMapper, FrittoIngredienteRepository frittoIngredienteRepository) {
        this.modelMapper = modelMapper;
        this.frittoIngredienteRepository = frittoIngredienteRepository;
    }

    public FriedIngredient convertToEntity(FrittoIngredienteDTO frittoIngredienteDTO){
        return (frittoIngredienteDTO != null) ? modelMapper.map(frittoIngredienteDTO, FriedIngredient.class) : null;
    }

    public FrittoIngredienteDTO convertToDTO(FriedIngredient friedIngredient){
        return (friedIngredient != null) ? modelMapper.map(friedIngredient, FrittoIngredienteDTO.class) : null;
    }
}
