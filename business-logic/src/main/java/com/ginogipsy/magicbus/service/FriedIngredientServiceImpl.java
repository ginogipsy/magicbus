package com.ginogipsy.magicbus.service;

import com.ginogipsy.magicbus.component.StringUtility;
import com.ginogipsy.magicbus.dto.FriedDTO;
import com.ginogipsy.magicbus.dto.FriedIngredientDTO;
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
public class FriedIngredientServiceImpl implements FriedIngredientService {

    private final MapperFactory mapperFactory;
    private final StringUtility stringUtility;
    @Override
    public FriedIngredientDTO insertIngredient(final String friedName, final String ingredientName) {

        final FriedDTO friedDTO = privateFindFriedByName(friedName)
                .orElseThrow(() -> new MagicbusException(FRIED_NOT_FOUND));

        final IngredientDTO ingredientDTO = privateFindIngredientByName(ingredientName)
                .orElseThrow(() -> new MagicbusException(INGREDIENT_NOT_FOUND));

        if (mapperFactory.getFriedIngredientMapper().findByFriedAndIngredient(friedDTO, ingredientDTO).isPresent()) {
            final String stringResult = "It is already present this ingredient " + ingredientName + " for this fried " + friedName + "!";
            log.error("FriedIngredientServiceImpl - insertIngredient() -> {}", stringResult);
            throw new MagicbusException(INGREDIENT_DOUGH_IS_ALREADY_PRESENT, stringResult);
        }

        log.info("FriedIngredientServiceImpl - insertIngredient() -> Saving the friedIngredient..");
        final FriedIngredientDTO friedIngredientDTO = new FriedIngredientDTO();
        friedIngredientDTO.setIngredient(ingredientDTO);
        friedIngredientDTO.setFried(friedDTO);
        return mapperFactory.getFriedIngredientMapper()
                .save(friedIngredientDTO)
                .orElseThrow(() -> new MagicbusException(SAVE_FAILED));
    }

    @Override
    public List<String> insertIngredients(final String friedName, final List<String> ingredientList) {
        final FriedDTO friedDTO = privateFindFriedByName(friedName)
                .orElseThrow(() -> new MagicbusException(INGREDIENT_NOT_FOUND));

        final List<String> addedIngredientList = new ArrayList<>();

        for (final String ingredientName :
                ingredientList) {

            final Optional<IngredientDTO> ingredientDTO = privateFindIngredientByName(ingredientName);

            if (ingredientDTO.isEmpty()) {
                log.warn("FriedIngredientServiceImpl - insertIngredients() -> Ingredient named '{}' doesn't exist!", ingredientName);
                continue;
            }

            if (mapperFactory.getFriedIngredientMapper().findByFriedAndIngredient(friedDTO, ingredientDTO.get()).isPresent()) {
                final String stringResult = "It is already present this ingredient \"" + ingredientName + "\" for this fried \"" + friedName + "\"!";
                log.warn("FriedIngredientServiceImpl - insertIngredients() -> {}", stringResult);
                continue;
            }
            log.info("FriedIngredientServiceImpl - insertIngredients() -> Adding ingredient named '{}'..", ingredientName);

            final FriedIngredientDTO friedIngredientDTO = new FriedIngredientDTO();
            friedIngredientDTO.setIngredient(ingredientDTO.get());
            friedIngredientDTO.setFried(friedDTO);
            mapperFactory.getFriedIngredientMapper().save(friedIngredientDTO);
            addedIngredientList.add(friedIngredientDTO.getIngredient().getName());
        }
        return addedIngredientList;
    }

    @Override
    public List<IngredientDTO> findIngredientListByFried(final String friedName) {
        final Optional<FriedDTO> friedDTO = privateFindFriedByName(friedName);

        if (friedDTO.isEmpty()) {
            log.warn("FriedIngredientServiceImpl - findIngredientListByFried() -> Fried named '{}' doesn't exist!", friedName);
            return new ArrayList<>();
        }

        log.info("FriedIngredientServiceImpl - findFriedListByIngredient() -> Finding ingredient list.. ");
        return mapperFactory.getFriedIngredientMapper()
                .findByFried(friedDTO.get())
                .stream()
                .map(FriedIngredientDTO::getIngredient)
                .toList();
    }

    @Override
    public List<FriedDTO> findFriedListByIngredient(final String ingredientName) {
        final Optional<IngredientDTO> ingredientDTO = privateFindIngredientByName(ingredientName);
        if (ingredientDTO.isEmpty()) {
            log.warn("FriedIngredientServiceImpl - findFriedListByIngredient() -> Ingredient named '{}' doesn't exist!", ingredientName);
            return new ArrayList<>();
        }
        log.info("FriedIngredientServiceImpl - findFriedListByIngredient() -> Finding fried list.. ");
        return mapperFactory.getFriedIngredientMapper().findByIngredient(ingredientDTO.get())
                .stream()
                .map(FriedIngredientDTO::getFried)
                .toList();
    }

    @Override
    public String deleteByFriedAndIngredient(final String friedName, final String ingredientName) {
        final FriedDTO friedDTO = privateFindFriedByName(friedName)
                .orElseThrow(() -> new MagicbusException(FRIED_NOT_FOUND));

        final IngredientDTO ingredientDTO = privateFindIngredientByName(ingredientName)
                .orElseThrow(() -> new MagicbusException(INGREDIENT_NOT_FOUND));

        log.info("FriedIngredientServiceImpl - deleteByFriedAndIngredient() -> Deleting fried/ingredient..");
        return mapperFactory.getFriedIngredientMapper().deleteByFriedAndIngredient(friedDTO, ingredientDTO);
    }

    private Optional<FriedDTO> privateFindFriedByName(final String friedName) {
        stringUtility.formatAllLower(friedName);
        log.info("FriedIngredientServiceImpl - privateFindFriedByName() -> Finding fried named '{}'..", friedName);

        return Optional.ofNullable(friedName)
                .flatMap(n -> mapperFactory.getFriedMapper().findByName(n));
    }

    private Optional<IngredientDTO> privateFindIngredientByName(final String ingredientName) {
        stringUtility.formatAllLower(ingredientName);
        log.info("FriedIngredientServiceImpl - privateFindIngredientByName() -> Finding ingredient named '{}'..", ingredientName);

        return Optional.ofNullable(ingredientName)
                .flatMap(n -> mapperFactory.getIngredientMapper().findByName(n));
    }
}
