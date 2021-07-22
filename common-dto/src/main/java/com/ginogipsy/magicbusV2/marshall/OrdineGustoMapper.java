package com.ginogipsy.magicbusV2.marshall;

import com.ginogipsy.magicbusV2.domain.OrdineGusto;
import com.ginogipsy.magicbusV2.dto.OrdineGustoDTO;
import com.ginogipsy.magicbusV2.repository.OrdineGustoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class OrdineGustoMapper {

    private final ModelMapper modelMapper;
    private final OrdineGustoRepository ordineGustoRepository;

    public OrdineGustoMapper(ModelMapper modelMapper, OrdineGustoRepository ordineGustoRepository) {
        this.modelMapper = modelMapper;
        this.ordineGustoRepository = ordineGustoRepository;
    }

    public OrdineGusto convertToEntity(OrdineGustoDTO ordineGustoDTO){
        return (ordineGustoDTO != null) ? modelMapper.map(ordineGustoDTO, OrdineGusto.class) : null;
    }

    public OrdineGustoDTO convertToDTO(OrdineGusto ordineGusto){
        return (ordineGusto != null) ? modelMapper.map(ordineGusto, OrdineGustoDTO.class) : null;
    }
}
