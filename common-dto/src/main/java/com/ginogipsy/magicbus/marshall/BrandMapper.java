package com.ginogipsy.magicbus.marshall;

import com.ginogipsy.magicbus.domain.Brand;
import com.ginogipsy.magicbus.dto.BrandDTO;
import com.ginogipsy.magicbus.repository.BrandRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class BrandMapper {

    private final ModelMapper modelMapper;
    private final BrandRepository brandRepository;

    public BrandMapper(ModelMapper modelMapper, BrandRepository brandRepository) {
        this.modelMapper = modelMapper;
        this.brandRepository = brandRepository;
    }

    public Brand convertToEntity(BrandDTO brandDTO){
        return (brandDTO != null) ? modelMapper.map(brandDTO, Brand.class) : null;
    }

    public BrandDTO convertToDTO(Brand brand){
        return (brand != null) ? modelMapper.map(brand, BrandDTO.class) : null;
    }
}
