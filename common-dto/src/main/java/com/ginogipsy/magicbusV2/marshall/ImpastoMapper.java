package com.ginogipsy.magicbusV2.marshall;

import com.ginogipsy.magicbusV2.domain.Bibita;
import com.ginogipsy.magicbusV2.domain.Impasto;
import com.ginogipsy.magicbusV2.dto.BibitaDTO;
import com.ginogipsy.magicbusV2.dto.ImpastoDTO;
import com.ginogipsy.magicbusV2.repository.ImpastoRepository;
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
