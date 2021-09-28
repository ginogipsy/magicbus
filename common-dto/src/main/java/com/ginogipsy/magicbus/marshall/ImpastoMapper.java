package com.ginogipsy.magicbus.marshall;

import com.ginogipsy.magicbus.domain.Impasto;
import com.ginogipsy.magicbus.dto.ImpastoDTO;
import com.ginogipsy.magicbus.repository.ImpastoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ImpastoMapper {

    private final ModelMapper modelMapper;
    private final ImpastoRepository impastoRepository;

    public ImpastoMapper(ModelMapper modelMapper, ImpastoRepository impastoRepository) {
        this.modelMapper = modelMapper;
        this.impastoRepository = impastoRepository;
    }

    public Impasto convertToEntity(ImpastoDTO impastoDTO){
        return (impastoDTO != null) ? modelMapper.map(impastoDTO, Impasto.class) : null;
    }

    public ImpastoDTO convertToDTO(Impasto impasto){
        return (impasto != null) ? modelMapper.map(impasto, ImpastoDTO.class) : null;
    }
}
