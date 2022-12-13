package com.ginogipsy.magicbus.service;

import com.ginogipsy.magicbus.component.StringUtility;
import com.ginogipsy.magicbus.dto.AllergenDTO;
import com.ginogipsy.magicbus.exceptionhandler.BeErrorCodeEnum;
import com.ginogipsy.magicbus.exceptionhandler.MagicbusException;
import com.ginogipsy.magicbus.marshall.MapperFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author ginogipsy
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class AllergenServiceImpl implements AllergenService {

    private final StringUtility stringUtility;
    private final MapperFactory mapperFactory;

    @Override
    public List<AllergenDTO> findAll() {
        return mapperFactory.getAllergenMapper().findAll();
    }

    @Override
    public AllergenDTO findByName(final String allergenName) {
        return privateFindByName(allergenName)
                .orElseThrow(() -> new MagicbusException(BeErrorCodeEnum.ALLERGEN_NOT_FOUND, "Allergen named " + allergenName + " not found"));
    }

    @Override
    public AllergenDTO insert(final AllergenDTO allergenDTO) {
        log.info("AllergenServiceImpl - insert() -> Checking if allergen is null..");
        final String allergenName = Optional.ofNullable(allergenDTO)
                .map(AllergenDTO::getName)
                .orElseThrow(() -> new MagicbusException(BeErrorCodeEnum.ALLERGEN_IS_NULL));

        if (privateFindByName(allergenName).isPresent()) {
            log.error("AllergenServiceImpl - insert() -> It is already present an allergen called {}!", allergenName);
            throw new MagicbusException(BeErrorCodeEnum.ALLERGEN_IS_ALREADY_PRESENT, "It is already present an allergen called " + allergenName + "!");
        }

        privateFormatAllergen(allergenDTO);
        log.info("AllergenServiceImpl - insert() -> Saving the allergen..");

        return mapperFactory.getAllergenMapper().save(allergenDTO)
                .orElseThrow(() -> new MagicbusException(BeErrorCodeEnum.SAVE_FAILED));
    }

    private Optional<AllergenDTO> privateFindByName(final String allergenName) {
        stringUtility.formatAllLower(allergenName);
        log.info("AllergenServiceImpl - privateFindByName() -> Finding allergen named '{}'..", allergenName);
        return Optional.ofNullable(allergenName)
                .flatMap(n -> mapperFactory.getAllergenMapper().findByName(n));
    }

    private void privateFormatAllergen(final AllergenDTO allergenDTO) {
        log.info("AllergenServiceImpl - privateFormatAllergen() -> Formatting name and description..");

        if(allergenDTO == null) {
            log.warn("AllergenServiceImpl - privateFormatAllergen() -> allergenDTO is null!");
            return;
        }

        Optional.ofNullable(allergenDTO.getName())
                .ifPresent(n -> allergenDTO.setName(stringUtility.formatAllLower(n)));
        Optional.ofNullable(allergenDTO.getDescription())
                .ifPresent(n -> allergenDTO.setDescription(stringUtility.formatAllLower(n)));
    }
}
