package com.ginogipsy.magicbusV2.marshall;


import com.ginogipsy.magicbusV2.domain.Ingrediente;

import com.ginogipsy.magicbusV2.dto.IngredienteDTO;
import com.ginogipsy.magicbusV2.repository.IngredienteRepository;
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

    public Ingrediente convertToEntity(IngredienteDTO ingredienteDTO){
        return (ingredienteDTO != null) ? modelMapper.map(ingredienteDTO, Ingrediente.class) : null;
    }

    public IngredienteDTO convertToDTO(Ingrediente ingrediente){
        return (ingrediente != null) ? modelMapper.map(ingrediente, IngredienteDTO.class) : null;
    }
}
