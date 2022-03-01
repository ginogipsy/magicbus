package com.ginogipsy.magicbus.marshall;

import com.ginogipsy.magicbus.domain.Brand;
import com.ginogipsy.magicbus.dto.BrandDTO;
import com.ginogipsy.magicbus.repository.BrandRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Slf4j
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

    public BrandDTO convertToDTO(final Brand brand) {
        return Optional.ofNullable(brand)
                .map(b -> modelMapper.map(b, BrandDTO.class))
                .orElse(null);
    }

    public BrandDTO save(final BrandDTO brandDTO) {
        log.info("Saving allergen on db..");
        return Optional.ofNullable(brandDTO)
                .map(this::convertToEntity)
                .map(brandRepository::save)
                .map(this::convertToDTO)
                .orElse(null);
    }

    public BrandDTO findByName(final String name) {
        log.info("Searching allergen where the name is " + name + "..");
        return Optional.ofNullable(name)
                .map(brandRepository::findByName)
                .map(this::convertToDTO)
                .orElse(null);
    }

    public List<BrandDTO> findAll() {
        return brandRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .toList();
    }
}
