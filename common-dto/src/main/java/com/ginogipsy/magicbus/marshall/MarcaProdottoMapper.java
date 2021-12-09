package com.ginogipsy.magicbus.marshall;

import com.ginogipsy.magicbus.domain.Brand;
import com.ginogipsy.magicbus.dto.MarcaProdottoDTO;
import com.ginogipsy.magicbus.repository.BrandRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class MarcaProdottoMapper {

    private final ModelMapper modelMapper;
    private final BrandRepository brandRepository;

    public MarcaProdottoMapper(ModelMapper modelMapper, BrandRepository brandRepository) {
        this.modelMapper = modelMapper;
        this.brandRepository = brandRepository;
    }

    public Brand convertToEntity(MarcaProdottoDTO marcaProdottoDTO){
        return (marcaProdottoDTO != null) ? modelMapper.map(marcaProdottoDTO, Brand.class) : null;
    }

    public MarcaProdottoDTO convertToDTO(Brand brand){
        return (brand != null) ? modelMapper.map(brand, MarcaProdottoDTO.class) : null;
    }
}
