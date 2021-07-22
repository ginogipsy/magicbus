package com.ginogipsy.magicbusV2.marshall;

import com.ginogipsy.magicbusV2.domain.FrittoIngrediente;
import com.ginogipsy.magicbusV2.dto.FrittoIngredienteDTO;
import com.ginogipsy.magicbusV2.repository.FrittoIngredienteRepository;
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

    public FrittoIngrediente convertToEntity(FrittoIngredienteDTO frittoIngredienteDTO){
        return (frittoIngredienteDTO != null) ? modelMapper.map(frittoIngredienteDTO, FrittoIngrediente.class) : null;
    }

    public FrittoIngredienteDTO convertToDTO(FrittoIngrediente frittoIngrediente){
        return (frittoIngrediente != null) ? modelMapper.map(frittoIngrediente, FrittoIngredienteDTO.class) : null;
    }
}
