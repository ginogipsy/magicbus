package com.ginogipsy.magicbus.marshall;

import com.ginogipsy.magicbus.domain.DoughIngredient;
import com.ginogipsy.magicbus.dto.ImpastoIngredienteDTO;
import com.ginogipsy.magicbus.repository.ImpastoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ImpastoIngredienteMapper {

    private final ModelMapper modelMapper;
    private final ImpastoRepository impastoRepository;

    public ImpastoIngredienteMapper(ModelMapper modelMapper, ImpastoRepository impastoRepository) {
        this.modelMapper = modelMapper;
        this.impastoRepository = impastoRepository;
    }

    public DoughIngredient convertToEntity(ImpastoIngredienteDTO impastoIngredienteDTO){
        return (impastoIngredienteDTO != null) ? modelMapper.map(impastoIngredienteDTO, DoughIngredient.class) : null;
    }

    public ImpastoIngredienteDTO convertToDTO(DoughIngredient doughIngredient){
        return (doughIngredient != null) ? modelMapper.map(doughIngredient, ImpastoIngredienteDTO.class) : null;
    }
}
