package com.ginogipsy.magicbus.marshall;

import com.ginogipsy.magicbus.domain.ImpastoIngrediente;
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

    public ImpastoIngrediente convertToEntity(ImpastoIngredienteDTO impastoIngredienteDTO){
        return (impastoIngredienteDTO != null) ? modelMapper.map(impastoIngredienteDTO, ImpastoIngrediente.class) : null;
    }

    public ImpastoIngredienteDTO convertToDTO(ImpastoIngrediente impastoIngrediente){
        return (impastoIngrediente != null) ? modelMapper.map(impastoIngrediente, ImpastoIngredienteDTO.class) : null;
    }
}
