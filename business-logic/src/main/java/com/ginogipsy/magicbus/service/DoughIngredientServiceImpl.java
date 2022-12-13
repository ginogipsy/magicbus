package com.ginogipsy.magicbus.service;

import com.ginogipsy.magicbus.component.StringUtility;
import com.ginogipsy.magicbus.dto.DoughDTO;
import com.ginogipsy.magicbus.dto.DoughIngredientDTO;
import com.ginogipsy.magicbus.dto.IngredientDTO;
import com.ginogipsy.magicbus.exceptionhandler.MagicbusException;
import com.ginogipsy.magicbus.marshall.MapperFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.ginogipsy.magicbus.exceptionhandler.BeErrorCodeEnum.*;

/**
 * @author ginogipsy
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class DoughIngredientServiceImpl implements DoughIngredientService {

    private final MapperFactory mapperFactory;
    private final StringUtility stringUtility;


    @Override
    public DoughIngredientDTO insertIngredient(final String doughName, final String ingredientName) {
        final DoughDTO doughDTO = privateFindDoughByName(doughName)
                .orElseThrow(() -> new MagicbusException(DOUGH_NOT_FOUND));

        final IngredientDTO ingredientDTO = privateFindIngredientByName(ingredientName)
                .orElseThrow(() -> new MagicbusException(INGREDIENT_NOT_FOUND));

        if (mapperFactory.getDoughIngredientMapper().findByDoughAndIngredient(doughDTO, ingredientDTO).isPresent()) {
            final String stringResult = "It is already present this ingredient " + ingredientName + " for this dough " + doughName + "!";
            log.error("DoughIngredientServiceImpl - insertIngredient() -> {}", stringResult);
            throw new MagicbusException(INGREDIENT_DOUGH_IS_ALREADY_PRESENT, stringResult);
        }

        log.info("DoughIngredientServiceImpl - insertIngredient() -> Saving the doughIngredient..");
        final DoughIngredientDTO doughIngredientDTO = new DoughIngredientDTO();
        doughIngredientDTO.setIngredient(ingredientDTO);
        doughIngredientDTO.setDough(doughDTO);
        return mapperFactory.getDoughIngredientMapper().save(doughIngredientDTO)
                .orElseThrow(() -> new MagicbusException(SAVE_FAILED));
    }

    @Override
    public List<String> insertIngredients(final String doughName, final List<String> ingredientList) {
        final DoughDTO doughDTO = privateFindDoughByName(doughName)
                .orElseThrow(() -> new MagicbusException(DOUGH_NOT_FOUND));

        final List<String> ingredientAddedResultList = new ArrayList<>();

        for (final String ingredientName :
                ingredientList) {

            final Optional<IngredientDTO> ingredientDTO = privateFindIngredientByName(ingredientName);

            if(ingredientDTO.isEmpty()) {
                log.warn("DoughIngredientServiceImpl - insertIngredients() -> Ingredient named '{}' doesn't exist!", ingredientName);
                continue;
            }

            if(mapperFactory.getDoughIngredientMapper().findByDoughAndIngredient(doughDTO, ingredientDTO.get()).isPresent()) {
                final String stringResult = "It is already present this ingredient \"" + ingredientName + "\" for this dough \"" + doughName + "\"!";
                log.warn("DoughIngredientServiceImpl - insertIngredients() -> {}", stringResult);
                continue;
            }

            log.info("DoughIngredientServiceImpl - insertIngredients() -> Adding ingredient named '{}'..", ingredientName);
            final DoughIngredientDTO doughIngredientDTO = new DoughIngredientDTO();
            doughIngredientDTO.setIngredient(ingredientDTO.get());
            doughIngredientDTO.setDough(doughDTO);
            mapperFactory.getDoughIngredientMapper().save(doughIngredientDTO);
            ingredientAddedResultList.add(doughIngredientDTO.getIngredient().getName());
        }
        return ingredientAddedResultList;
    }

    @Override
    public List<IngredientDTO> findIngredientListByDough(final String doughName) {
        final Optional<DoughDTO> doughDTO = privateFindDoughByName(doughName);

        if (doughDTO.isEmpty()) {
            log.warn("DoughIngredientServiceImpl - findIngredientListByDough() -> Dough named '{}' doesn't exist!", doughName);
            return new ArrayList<>();
        }

        log.info("DoughIngredientServiceImpl - findIngredientListByDough() -> Finding ingredient list.. ");
        return mapperFactory.getDoughIngredientMapper().findByDough(doughDTO.get())
                .stream()
                .map(DoughIngredientDTO::getIngredient)
                .toList();
    }

    @Override
    public List<DoughDTO> findDoughListByIngredient(final String ingredientName) {
        final Optional<IngredientDTO> ingredientDTO = privateFindIngredientByName(ingredientName);

        if (ingredientDTO.isEmpty()) {
            log.warn("DoughIngredientServiceImpl - findDoughListByIngredient() -> Ingredient named '{}' doesn't exist!", ingredientName);
            return new ArrayList<>();
        }

        log.info("DoughIngredientServiceImpl - findDoughListByIngredient() -> Finding dough list.. ");
        return mapperFactory.getDoughIngredientMapper()
                .findByIngredient(ingredientDTO.get())
                .stream()
                .map(DoughIngredientDTO::getDough)
                .toList();
    }

    @Override
    public String deleteByDoughAndIngredient(final String doughName, final String ingredientName) {
        final DoughDTO doughDTO = privateFindDoughByName(doughName)
                .orElseThrow(() -> new MagicbusException(DOUGH_NOT_FOUND));

        final IngredientDTO ingredientDTO = privateFindIngredientByName(ingredientName)
                .orElseThrow(() -> new MagicbusException(INGREDIENT_NOT_FOUND));

        log.info("DoughIngredientServiceImpl - deleteByDoughAndIngredient() -> Deleting dough/ingredient..");
        return mapperFactory.getDoughIngredientMapper().deleteByDoughAndIngredient(doughDTO, ingredientDTO);
    }

    private Optional<DoughDTO> privateFindDoughByName(final String doughName) {
        stringUtility.formatAllLower(doughName);
        log.info("DoughIngredientServiceImpl - privateFindDoughByName() -> Finding dough named '{}'..", doughName);
        return Optional.ofNullable(doughName)
                .flatMap(n -> mapperFactory.getDoughMapper().findByName(n));
    }

    private Optional<IngredientDTO> privateFindIngredientByName(final String ingredientName) {
        stringUtility.formatAllLower(ingredientName);
        log.info("DoughIngredientServiceImpl - privateFindIngredientByName() -> Finding ingredient named '{}'..", ingredientName);
        return Optional.ofNullable(ingredientName)
                .flatMap(n -> mapperFactory.getIngredientMapper().findByName(n));
    }
}
