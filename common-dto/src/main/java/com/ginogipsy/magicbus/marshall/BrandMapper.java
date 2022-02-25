package com.ginogipsy.magicbus.marshall;

import com.ginogipsy.magicbus.domain.Brand;
import com.ginogipsy.magicbus.dto.BrandDTO;
import com.ginogipsy.magicbus.repository.BrandRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class BrandMapper {

    private final ModelMapper modelMapper;
    private final BrandRepository brandRepository;

    public BrandMapper(ModelMapper modelMapper, BrandRepository brandRepository) {
        this.modelMapper = modelMapper;
        this.brandRepository = brandRepository;
    }

    public Brand convertToEntity(final BrandDTO brandDTO){
        return Optional.ofNullable(brandDTO)
                .map(b -> modelMapper.map(b, Brand.class))
            .orElse(null);
    }

    public BrandDTO convertToDTO(final Brand brand){
        return Optional.ofNullable(brand)
                .map(b -> modelMapper.map(b, BrandDTO.class))
            .orElse(null);
    }
}
