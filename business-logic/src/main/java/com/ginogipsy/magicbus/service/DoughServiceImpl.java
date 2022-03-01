package com.ginogipsy.magicbus.service;

import com.ginogipsy.magicbus.component.StringUtility;
import com.ginogipsy.magicbus.customexception.controller.DataNotCorrectException;
import com.ginogipsy.magicbus.customexception.notfound.ObjectNotFoundException;
import com.ginogipsy.magicbus.dto.DoughDTO;
import com.ginogipsy.magicbus.marshall.MapperFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class DoughServiceImpl implements DoughService {

    private final StringUtility stringUtility;
    private final MapperFactory mapperFactory;

    public DoughServiceImpl(StringUtility stringUtility, MapperFactory mapperFactory) {
        this.stringUtility = stringUtility;
        this.mapperFactory = mapperFactory;
    }

    @Override
    public DoughDTO findByName(String name) {
        return Optional.ofNullable(privateFindByName(name))
                .orElseThrow(() -> new ObjectNotFoundException("Ingredient " + name + " not found!"));
    }

    @Override
    public List<DoughDTO> findAll() {
        return mapperFactory.getDoughMapper().findAll();
    }

    @Override
    public DoughDTO save(DoughDTO doughDTO) {
        log.info("Checking if this dough is already present..");
        final String name = Optional.ofNullable(doughDTO)
                .map(DoughDTO::getName)
                .orElseThrow(() -> new DataNotCorrectException("Ingredient doesn't have to be null!"));

        if (Optional.ofNullable(privateFindByName(name)).isPresent()) {
            log.error("It is already present a dough called " + name + "!");
            throw new DataNotCorrectException("It is already present a dough called " + name + "!");
        }

        log.info("Formatting name and description..");
        privateFormatIngredient(doughDTO);
        log.info("Saving the ingredient..");
        return mapperFactory.getDoughMapper().save(doughDTO);
    }

    private DoughDTO privateFindByName(final String name) {
        return Optional.ofNullable(name)
                .map(n -> mapperFactory.getDoughMapper().findByName(n))
                .orElse(null);
    }

    private void privateFormatIngredient(final DoughDTO doughDTO) {
        Optional.ofNullable(doughDTO.getName())
                .ifPresent(n -> doughDTO.setName(stringUtility.formatAllMinusc(n)));
        Optional.ofNullable(doughDTO.getDescription())
                .ifPresent(n -> doughDTO.setDescription(stringUtility.formatAllMinusc(n)));
    }
}
