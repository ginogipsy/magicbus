package com.ginogipsy.magicbus.service;

import com.ginogipsy.magicbus.component.StringUtility;
import com.ginogipsy.magicbus.dto.IngredientDTO;
import com.ginogipsy.magicbus.dto.ToppingDTO;
import com.ginogipsy.magicbus.dto.ToppingIngredientDTO;
import com.ginogipsy.magicbus.exceptionhandler.BeErrorCodeEnum;
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
public class ToppingIngredientServiceImpl implements ToppingIngredientService {

    private final MapperFactory mapperFactory;
    private final StringUtility stringUtility;

    @Override
    public ToppingIngredientDTO insertIngredient(final String toppingName, final String ingredientName) {
        final ToppingDTO toppingDTO = privateFindToppingByName(toppingName)
                .orElseThrow(() -> new MagicbusException(BeErrorCodeEnum.TOPPING_NOT_FOUND));
        final IngredientDTO ingredientDTO = privateFindIngredientByName(ingredientName)
                .orElseThrow(() -> new MagicbusException(BeErrorCodeEnum.INGREDIENT_NOT_FOUND));

        if (mapperFactory.getToppingIngredientMapper().findByToppingAndIngredient(toppingDTO, ingredientDTO).isPresent()) {
            final String stringResult = "It is already present this ingredient " + ingredientName + " for this topping " + toppingName + "!";
            log.error("ToppingIngredientServiceImpl - insertIngredient() -> {}", stringResult);
            throw new MagicbusException(INGREDIENT_TOPPING_IS_ALREADY_PRESENT, stringResult);
        }

        log.info("ToppingIngredientServiceImpl - insertIngredient() -> Saving the doughIngredient..");
        final ToppingIngredientDTO toppingIngredientDTO = new ToppingIngredientDTO();
        toppingIngredientDTO.setIngredient(ingredientDTO);
        toppingIngredientDTO.setTopping(toppingDTO);
        return mapperFactory.getToppingIngredientMapper().save(toppingIngredientDTO)
                .orElseThrow(() -> new MagicbusException(SAVE_FAILED));
    }

    @Override
    public List<String> insertIngredients(final String toppingName, final List<String> ingredientList) {
        final ToppingDTO toppingDTO = privateFindToppingByName(toppingName)
                .orElseThrow(() -> new MagicbusException(TOPPING_NOT_FOUND));

        final List<String> addedIngredientList = new ArrayList<>();

        for (final String ingredientName :
                ingredientList) {
            final Optional<IngredientDTO> ingredientDTO = privateFindIngredientByName(ingredientName);

            if (ingredientDTO.isEmpty()) {
                log.warn("ToppingIngredientServiceImpl - insertIngredients() -> Ingredient named '{}' doesn't exist!", ingredientName);
                continue;
            }

            if (mapperFactory.getToppingIngredientMapper().findByToppingAndIngredient(toppingDTO, ingredientDTO.get()).isPresent()) {
                final String stringResult = "It is already present this ingredient \"" + ingredientName + "\" for this topping \"" + toppingName + "\"!";
                log.warn("ToppingIngredientServiceImpl - insertIngredients() -> {}", stringResult);
                continue;
            }

            log.info("ToppingIngredientServiceImpl - insertIngredients() -> Adding ingredient named '{}'..", ingredientName);
            final ToppingIngredientDTO toppingIngredientDTO = new ToppingIngredientDTO();
            toppingIngredientDTO.setIngredient(ingredientDTO.get());
            toppingIngredientDTO.setTopping(toppingDTO);
            mapperFactory.getToppingIngredientMapper().save(toppingIngredientDTO);
            addedIngredientList.add(toppingIngredientDTO.getIngredient().getName());
        }
        return addedIngredientList;
    }

    @Override
    public List<IngredientDTO> findIngredientListByTopping(final String toppingName) {
        final Optional<ToppingDTO> toppingDTO = privateFindToppingByName(toppingName);
        if (toppingDTO.isEmpty()) {
            log.warn("ToppingIngredientServiceImpl - findIngredientListByTopping() -> Topping named '{}' doesn't exist!", toppingName);
            return new ArrayList<>();
        }

        log.info("ToppingIngredientServiceImpl - findIngredientListByTopping() -> Finding ingredient list.. ");
        return mapperFactory.getToppingIngredientMapper().findByTopping(toppingDTO.get())
                .stream()
                .map(ToppingIngredientDTO::getIngredient)
                .toList();
    }

    @Override
    public List<ToppingDTO> findToppingListByIngredient(final String ingredientName) {
        final Optional<IngredientDTO> ingredientDTO = privateFindIngredientByName(ingredientName);

        if (ingredientDTO.isEmpty()) {
            log.warn("ToppingIngredientServiceImpl - findToppingListByIngredient() -> Ingredient named '{}' doesn't exist!", ingredientName);
            return new ArrayList<>();
        }
        log.info("ToppingIngredientServiceImpl - findToppingListByIngredient() -> Finding topping list.. ");
        return mapperFactory.getToppingIngredientMapper().findByIngredient(ingredientDTO.get())
                .stream()
                .map(ToppingIngredientDTO::getTopping)
                .toList();
    }

    @Override
    public String deleteByToppingAndIngredient(final String toppingName, final String ingredientName) {
        final ToppingDTO toppingDTO = privateFindToppingByName(toppingName)
                .orElseThrow(() -> new MagicbusException(TOPPING_NOT_FOUND));

        final IngredientDTO ingredientDTO = privateFindIngredientByName(ingredientName)
                .orElseThrow(() -> new MagicbusException(INGREDIENT_NOT_FOUND));

        log.info("ToppingIngredientServiceImpl - deleteByToppingAndIngredient() -> Deleting topping/ingredient..");
        return mapperFactory.getToppingIngredientMapper().deleteByToppingAndIngredient(toppingDTO, ingredientDTO);
    }

    private Optional<ToppingDTO> privateFindToppingByName(final String toppingName) {
        stringUtility.formatAllLower(toppingName);
        log.info("ToppingIngredientServiceImpl - privateFindToppingByName() -> Finding topping named '{}'..", toppingName);
        return Optional.ofNullable(toppingName)
                .flatMap(n -> mapperFactory.getToppingMapper().findByName(n));
    }

    private Optional<IngredientDTO> privateFindIngredientByName(final String ingredientName) {
        stringUtility.formatAllLower(ingredientName);
        log.info("ToppingIngredientServiceImpl - privateFindIngredientByName() -> Finding ingredient named '{}'..", ingredientName);
        return Optional.ofNullable(ingredientName)
                .flatMap(n -> mapperFactory.getIngredientMapper().findByName(n));
    }
}
