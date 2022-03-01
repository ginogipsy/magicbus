package com.ginogipsy.magicbus.service;

import com.ginogipsy.magicbus.component.StringUtility;
import com.ginogipsy.magicbus.customexception.controller.DataNotCorrectException;
import com.ginogipsy.magicbus.customexception.notfound.IngredientNotFoundException;
import com.ginogipsy.magicbus.dto.IngredientDTO;
import com.ginogipsy.magicbus.marshall.MapperFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {

    private final MapperFactory mapperFactory;
    private final StringUtility stringUtility;

    public IngredientServiceImpl(MapperFactory mapperFactory, StringUtility stringUtility) {
        this.mapperFactory = mapperFactory;
        this.stringUtility = stringUtility;
    }

    @Override
    public IngredientDTO findByName(final String name) {
        return Optional.ofNullable(privateFindByName(name))
                .orElseThrow(() -> new IngredientNotFoundException("Ingredient " + name + " not found!"));
    }

    @Override
    public List<IngredientDTO> findByNameContains(final String name) {
        return Optional.ofNullable(name)
                .map(n -> mapperFactory.getIngredientMapper().findByNameContains(n))
                .orElseThrow(() -> new IngredientNotFoundException("Ingredients that contains"));
    }

    @Override
    public IngredientDTO save(final IngredientDTO ingredientDTO) {
        log.info("Checking if this ingredient is already present..");
        final String name = Optional.ofNullable(ingredientDTO)
                .map(IngredientDTO::getName)
                .orElseThrow(() -> new DataNotCorrectException("Ingredient doesn't have to be null!"));

        if (Optional.ofNullable(privateFindByName(name)).isPresent()) {
            log.error("It is already present an ingredient called " + name + "!");
            throw new DataNotCorrectException("It is already present an ingredient called " + name + "!");
        }

        log.info("Formatting name and description..");
        privateFormatIngredient(ingredientDTO);
        log.info("Saving the ingredient..");
        return mapperFactory.getIngredientMapper().save(ingredientDTO);
    }

    private IngredientDTO privateFindByName(final String name) {
        return Optional.ofNullable(name)
                .map(n -> mapperFactory.getIngredientMapper().findByName(n))
                .orElse(null);
    }

    private void privateFormatIngredient(final IngredientDTO ingredientDTO) {
        Optional.ofNullable(ingredientDTO.getName())
                .ifPresent(n -> ingredientDTO.setName(stringUtility.formatAllMinusc(n)));
        Optional.ofNullable(ingredientDTO.getDescription())
                .ifPresent(n -> ingredientDTO.setDescription(stringUtility.formatAllMinusc(n)));
    }
}
