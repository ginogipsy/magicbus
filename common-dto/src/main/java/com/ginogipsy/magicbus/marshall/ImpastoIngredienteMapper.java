package com.ginogipsy.magicbus.marshall;

import com.ginogipsy.magicbus.domain.DoughIngredient;
import com.ginogipsy.magicbus.dto.ImpastoIngredienteDTO;
import com.ginogipsy.magicbus.repository.DoughRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ImpastoIngredienteMapper {

    private final ModelMapper modelMapper;
    private final DoughRepository doughRepository;

    public ImpastoIngredienteMapper(ModelMapper modelMapper, DoughRepository doughRepository) {
        this.modelMapper = modelMapper;
        this.doughRepository = doughRepository;
    }

    public DoughIngredient convertToEntity(ImpastoIngredienteDTO impastoIngredienteDTO){
        return (impastoIngredienteDTO != null) ? modelMapper.map(impastoIngredienteDTO, DoughIngredient.class) : null;
    }

    public ImpastoIngredienteDTO convertToDTO(DoughIngredient doughIngredient){
        return (doughIngredient != null) ? modelMapper.map(doughIngredient, ImpastoIngredienteDTO.class) : null;
    }
}
