package com.ginogipsy.magicbus.service;

import com.ginogipsy.magicbus.component.StringUtility;
import com.ginogipsy.magicbus.dto.BrandDTO;
import com.ginogipsy.magicbus.exceptionhandler.BeErrorCodeEnum;
import com.ginogipsy.magicbus.exceptionhandler.MagicbusException;
import com.ginogipsy.magicbus.marshall.MapperFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.ginogipsy.magicbus.exceptionhandler.BeErrorCodeEnum.*;

/**
 * @author ginogipsy
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {

    private final StringUtility stringUtility;
    private final MapperFactory mapperFactory;

    @Override
    public BrandDTO findByName(final String name) {
        return privateFindByName(name)
                .orElseThrow(() -> new MagicbusException(BRAND_IS_NULL));
    }
    @Override
    public List<BrandDTO> findAll() {
        return mapperFactory.getBrandMapper().findAll();
    }

    @Override
    public BrandDTO insert(final BrandDTO brandDTO) {
        log.info("BrandServiceImpl - insert() -> Checking if brand is null..");
        final String brandName = Optional.ofNullable(brandDTO)
                .map(BrandDTO::getName)
                .orElseThrow(() -> new MagicbusException(BRAND_IS_NULL));

        if (privateFindByName(brandName).isPresent()) {
            log.error("BrandServiceImpl - insert() -> It is already present a brand called {}!", brandName);
            throw new MagicbusException(BRAND_IS_ALREADY_PRESENT, "It is already present a brand called " + brandName + "!");
        }

        privateFormatBrand(brandDTO);
        log.info("BrandServiceImpl - insert() -> Saving the brand..");
        return mapperFactory.getBrandMapper().save(brandDTO)
                .orElseThrow(() -> new MagicbusException(SAVE_FAILED));
    }

    private Optional<BrandDTO> privateFindByName(final String brandName) {
        log.info("BrandServiceImpl - privateFindByName() -> Checking if brand name is null..");
        final String bn = Optional.ofNullable(brandName)
                .map(stringUtility::formatAllLower)
                .orElseThrow(() -> new MagicbusException(BeErrorCodeEnum.NAME_IS_NULL));
        log.info("BrandServiceImpl - privateFindByName() -> Finding brand named '{}'..", bn);
        return Optional.ofNullable(bn)
                .flatMap(n -> mapperFactory.getBrandMapper().findByName(n));
    }

    private void privateFormatBrand(final BrandDTO brandDTO) {
        log.info("BrandServiceImpl - privateFormatBrand() -> Formatting name and description..");

        if(brandDTO == null) {
            log.warn("BrandServiceImpl - privateFormatBrand() -> brandDTO is null!");
            return;
        }

        Optional.ofNullable(brandDTO.getName())
                .ifPresent(n -> brandDTO.setName(stringUtility.formatAllLower(n)));
        Optional.ofNullable(brandDTO.getDescription())
                .ifPresent(n -> brandDTO.setDescription(stringUtility.formatAllLower(n)));
    }
}
