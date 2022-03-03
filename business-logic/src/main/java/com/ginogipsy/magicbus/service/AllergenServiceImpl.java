package com.ginogipsy.magicbus.service;

import com.ginogipsy.magicbus.component.StringUtility;
import com.ginogipsy.magicbus.customexception.controller.DataNotCorrectException;
import com.ginogipsy.magicbus.customexception.notfound.AllergenNotFoundException;
import com.ginogipsy.magicbus.dto.AllergenDTO;
import com.ginogipsy.magicbus.marshall.MapperFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class AllergenServiceImpl implements AllergenService {

    private final StringUtility stringUtility;
    private final MapperFactory mapperFactory;

    public AllergenServiceImpl(StringUtility stringUtility, MapperFactory mapperFactory) {
        this.stringUtility = stringUtility;
        this.mapperFactory = mapperFactory;
    }

    @Override
    public List<AllergenDTO> findAll() {
        return mapperFactory.getAllergenMapper().findAll();
    }

    @Override
    public AllergenDTO findByName(final String name) {
        return Optional.ofNullable(privateFindByName(name))
                .orElseThrow(() -> new AllergenNotFoundException("Allergen named " + name + " not found"));
    }

    @Override
    public AllergenDTO insert(final AllergenDTO allergenDTO) {
        log.info("Checking if this allergen is already present..");
        final String name = Optional.ofNullable(allergenDTO)
                .map(AllergenDTO::getName)
                .orElseThrow(() -> new DataNotCorrectException("Allergen doesn't have to be null!"));

        if (Optional.ofNullable(privateFindByName(name)).isPresent()) {
            log.error("It is already present an ingredient called " + name + "!");
            throw new DataNotCorrectException("It is already present an allergen called " + name + "!");
        }

        log.info("Formatting name and description..");
        privateFormatIngredient(allergenDTO);
        log.info("Saving the ingredient..");
        return mapperFactory.getAllergenMapper().save(allergenDTO);
    }

    private AllergenDTO privateFindByName(final String name) {
        return Optional.ofNullable(name)
                .map(n -> mapperFactory.getAllergenMapper().findByName(n))
                .orElse(null);
    }

    private void privateFormatIngredient(final AllergenDTO allergenDTO) {
        Optional.ofNullable(allergenDTO.getName())
                .ifPresent(n -> allergenDTO.setName(stringUtility.formatAllMinusc(n)));
        Optional.ofNullable(allergenDTO.getDescription())
                .ifPresent(n -> allergenDTO.setDescription(stringUtility.formatAllMinusc(n)));
    }
}
