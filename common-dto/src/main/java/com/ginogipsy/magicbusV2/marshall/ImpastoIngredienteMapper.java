package com.ginogipsy.magicbusV2.marshall;

import com.ginogipsy.magicbusV2.domain.Bibita;
import com.ginogipsy.magicbusV2.domain.ImpastoIngrediente;
import com.ginogipsy.magicbusV2.dto.BibitaDTO;
import com.ginogipsy.magicbusV2.dto.ImpastoIngredienteDTO;
import com.ginogipsy.magicbusV2.repository.ImpastoRepository;
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
