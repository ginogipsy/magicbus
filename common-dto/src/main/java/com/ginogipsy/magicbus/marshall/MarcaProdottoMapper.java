package com.ginogipsy.magicbus.marshall;

import com.ginogipsy.magicbus.domain.Brand;
import com.ginogipsy.magicbus.dto.MarcaProdottoDTO;
import com.ginogipsy.magicbus.repository.MarcaProdottoRepository;
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

    public Brand convertToEntity(MarcaProdottoDTO marcaProdottoDTO){
        return (marcaProdottoDTO != null) ? modelMapper.map(marcaProdottoDTO, Brand.class) : null;
    }

    public MarcaProdottoDTO convertToDTO(Brand brand){
        return (brand != null) ? modelMapper.map(brand, MarcaProdottoDTO.class) : null;
    }
}
