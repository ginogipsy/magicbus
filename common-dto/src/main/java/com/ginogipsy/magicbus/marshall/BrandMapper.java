package com.ginogipsy.magicbus.marshall;

import com.ginogipsy.magicbus.domain.Brand;
import com.ginogipsy.magicbus.dto.BrandDTO;
import com.ginogipsy.magicbus.repository.BrandRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * @author ginogipsy
 */

@Slf4j
@Component
@RequiredArgsConstructor
public class BrandMapper {

    private final ModelMapper modelMapper;
    private final BrandRepository brandRepository;

    public Brand convertToEntity(final BrandDTO brandDTO) {
        return Optional.ofNullable(brandDTO)
                .map(b -> modelMapper.map(b, Brand.class))
                .orElse(null);
    }

    public BrandDTO convertToDTO(final Brand brand) {
        return Optional.ofNullable(brand)
                .map(b -> modelMapper.map(b, BrandDTO.class))
                .orElse(null);
    }

    public Optional<Brand> convertToEntity(final Optional<BrandDTO> brandDTO) {
        return Optional.ofNullable(brandDTO)
                .map(b -> modelMapper.map(b, Brand.class));
    }

    public Optional<BrandDTO> convertToDTO(final Optional<Brand> brand) {
        return Optional.ofNullable(brand)
                .map(b -> modelMapper.map(b, BrandDTO.class));
    }

    public Optional<BrandDTO> save(final BrandDTO brandDTO) {
        log.info("BrandMapper - save() -> Saving brand on db..");
        return Optional.ofNullable(brandDTO)
                .map(this::convertToEntity)
                .map(brandRepository::save)
                .map(this::convertToDTO);
    }

    public Optional<BrandDTO> findByName(final String name) {
        log.info("BrandMapper - findByName() -> Searching brand where name is '{}' ..", name);
        return Optional.ofNullable(name)
                .map(brandRepository::findByName)
                .map(this::convertToDTO)
                .orElse(null);
    }

    public List<BrandDTO> findAll() {
        log.info("BrandMapper - findAll() -> Looking for all the brands on db..");
        return brandRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .toList();
    }
}
