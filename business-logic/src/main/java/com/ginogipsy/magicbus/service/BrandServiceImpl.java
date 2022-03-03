package com.ginogipsy.magicbus.service;

import com.ginogipsy.magicbus.component.StringUtility;
import com.ginogipsy.magicbus.customexception.controller.DataNotCorrectException;
import com.ginogipsy.magicbus.customexception.notfound.ObjectNotFoundException;
import com.ginogipsy.magicbus.dto.BrandDTO;
import com.ginogipsy.magicbus.marshall.MapperFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class BrandServiceImpl implements BrandService {

    private final StringUtility stringUtility;
    private final MapperFactory mapperFactory;

    public BrandServiceImpl(StringUtility stringUtility, MapperFactory mapperFactory) {
        this.stringUtility = stringUtility;
        this.mapperFactory = mapperFactory;
    }

    @Override
    public BrandDTO findByName(final String name) {
        return Optional.ofNullable(privateFindByName(name))
                .orElseThrow(() -> new ObjectNotFoundException("Brand " + name + " not found!"));
    }

    @Override
    public List<BrandDTO> findAll() {
        return mapperFactory.getBrandMapper().findAll();
    }

    @Override
    public BrandDTO insert(BrandDTO brandDTO) {
        log.info("Checking if this brand is already present..");
        final String name = Optional.ofNullable(brandDTO)
                .map(BrandDTO::getName)
                .orElseThrow(() -> new DataNotCorrectException("Brand doesn't have to be null!"));

        if (Optional.ofNullable(privateFindByName(name)).isPresent()) {
            log.error("It is already present a brand called " + name + "!");
            throw new DataNotCorrectException("It is already present a brand called " + name + "!");
        }

        log.info("Formatting name and description..");
        privateFormatIngredient(brandDTO);
        log.info("Saving the ingredient..");
        return mapperFactory.getBrandMapper().save(brandDTO);
    }

    private BrandDTO privateFindByName(final String name) {
        return Optional.ofNullable(name)
                .map(n -> mapperFactory.getBrandMapper().findByName(n))
                .orElse(null);
    }

    private void privateFormatIngredient(final BrandDTO brandDTO) {
        Optional.ofNullable(brandDTO.getName())
                .ifPresent(n -> brandDTO.setName(stringUtility.formatAllMinusc(n)));
        Optional.ofNullable(brandDTO.getDescription())
                .ifPresent(n -> brandDTO.setDescription(stringUtility.formatAllMinusc(n)));
    }
}
