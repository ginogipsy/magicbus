package com.ginogipsy.magicbusV2.marshall;

import com.ginogipsy.magicbusV2.domain.MarcaProdotto;
import com.ginogipsy.magicbusV2.dto.MarcaProdottoDTO;
import com.ginogipsy.magicbusV2.repository.MarcaProdottoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class MarcaProdottoMapper {

    private final ModelMapper modelMapper;
    private final MarcaProdottoRepository marcaProdottoRepository;

    public MarcaProdottoMapper(ModelMapper modelMapper, MarcaProdottoRepository marcaProdottoRepository) {
        this.modelMapper = modelMapper;
        this.marcaProdottoRepository = marcaProdottoRepository;
    }

    public MarcaProdotto convertToEntity(MarcaProdottoDTO marcaProdottoDTO){
        return (marcaProdottoDTO != null) ? modelMapper.map(marcaProdottoDTO, MarcaProdotto.class) : null;
    }

    public MarcaProdottoDTO convertToDTO(MarcaProdotto marcaProdotto){
        return (marcaProdotto != null) ? modelMapper.map(marcaProdotto, MarcaProdottoDTO.class) : null;
    }
}
