package com.ginogipsy.magicbus.service;

import com.ginogipsy.magicbus.dto.FriedDTO;
import com.ginogipsy.magicbus.dto.FriedIngredientDTO;
import com.ginogipsy.magicbus.dto.IngredientDTO;
import com.ginogipsy.magicbus.exceptionhandler.MagicbusException;
import com.ginogipsy.magicbus.marshall.MapperFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.ginogipsy.magicbus.exceptionhandler.BeErrorCodeEnum.*;

@Slf4j
@Service
public class FriedIngredientServiceImpl implements FriedIngredientService {

    private final MapperFactory mapperFactory;

    public FriedIngredientServiceImpl(MapperFactory mapperFactory) {
        this.mapperFactory = mapperFactory;
    }

    @Override
    public FriedIngredientDTO insertIngredient(final String friedName, final String ingredientName) {
        log.info("Checking if this fried is present..");
        final FriedDTO friedDTO = Optional.ofNullable(privateFindFriedByName(friedName))
                .orElseThrow(() -> new MagicbusException(FRIED_NOT_FOUND));
        log.info("Checking if this ingredient is present..");
        final IngredientDTO ingredientDTO = Optional.ofNullable(privateFindIngredientByName(ingredientName))
                .orElseThrow(() -> new MagicbusException(INGREDIENT_NOT_FOUND));

        if (Optional.ofNullable(mapperFactory.getFriedIngredientMapper().findByFriedAndIngredient(friedDTO, ingredientDTO)).isPresent()) {
            log.error("It is already present this ingredient " + ingredientName + " for this fried " + friedName + "!");
            throw new MagicbusException(INGREDIENT_FRIED_IS_ALREADY_PRESENT, "It is already present this ingredient " + ingredientName + " for this fried " + friedName + "!");
        }
        log.info("Saving the friedIngredient..");
        final FriedIngredientDTO friedIngredientDTO = new FriedIngredientDTO();
        friedIngredientDTO.setIngredient(ingredientDTO);
        friedIngredientDTO.setFried(friedDTO);
        return mapperFactory.getFriedIngredientMapper().save(friedIngredientDTO);
    }

    @Override
    public List<String> insertIngredients(final String friedName, final List<String> ingredientList) {
        log.info("Checking if this fried is present..");
        final FriedDTO friedDTO = Optional.ofNullable(privateFindFriedByName(friedName))
                .orElseThrow(() -> new MagicbusException(INGREDIENT_NOT_FOUND));
        final List<String> addedIngredients = new ArrayList<>();
        for (final String ingredientName :
                ingredientList) {
            log.info("Checking if this ingredient is present..");
            final IngredientDTO ingredientDTO = privateFindIngredientByName(ingredientName);
            if (Optional.ofNullable(mapperFactory.getFriedIngredientMapper().findByFriedAndIngredient(friedDTO, ingredientDTO)).isPresent()) {
                final String error = "It is already present this ingredient \"" + ingredientName + "\" for this fried \"" + friedName + "\"!";
                log.error(error);
                addedIngredients.add(error);
                continue;
            }
            log.info("Saving the friedIngredient..");
            Optional.ofNullable(ingredientDTO).ifPresent(i -> {
                log.info("Saving the friedIngredient..");
                final FriedIngredientDTO friedIngredientDTO = new FriedIngredientDTO();
                friedIngredientDTO.setIngredient(i);
                friedIngredientDTO.setFried(friedDTO);
                mapperFactory.getFriedIngredientMapper().save(friedIngredientDTO);
                addedIngredients.add(friedIngredientDTO.getIngredient().getName());
            });
        }
        return addedIngredients;
    }

    @Override
    public List<IngredientDTO> findByFried(final String friedName) {
        log.info("Checking if this fried is present..");
        final FriedDTO friedDTO = privateFindFriedByName(friedName);
        if (Optional.ofNullable(friedDTO).isEmpty()) {
            log.warn("this dough doesn't exists..");
            return new ArrayList<>();
        }

        log.info("Start searching ingredients for this fried..");
        return Optional.ofNullable(mapperFactory.getFriedIngredientMapper().findByFried(friedDTO))
                .map(l -> l.stream()
                        .map(FriedIngredientDTO::getIngredient)
                        .toList())
                .orElse(new ArrayList<>());
    }

    @Override
    public List<FriedDTO> findByIngredient(final String ingredientName) {
        log.info("Checking if this ingredient is present..");
        final IngredientDTO ingredientDTO = privateFindIngredientByName(ingredientName);
        if (Optional.ofNullable(ingredientDTO).isEmpty()) {
            log.warn("this ingredient doesn't exists..");
            return new ArrayList<>();
        }
        log.info("Start searching fried for this ingredient..");
        return Optional.ofNullable(mapperFactory.getFriedIngredientMapper().findByIngredient(ingredientDTO))
                .map(l -> l.stream()
                        .map(FriedIngredientDTO::getFried)
                        .toList())
                .orElse(new ArrayList<>());
    }

    @Override
    public String deleteByFriedAndIngredient(String friedName, String ingredientName) {
        log.info("Checking if this fried is present..");
        final FriedDTO friedDTO = Optional.ofNullable(privateFindFriedByName(friedName))
                .orElseThrow(() -> new MagicbusException(FRIED_NOT_FOUND));
        log.info("Checking if this ingredient is present..");
        final IngredientDTO ingredientDTO = Optional.ofNullable(privateFindIngredientByName(ingredientName))
                .orElseThrow(() -> new MagicbusException(INGREDIENT_NOT_FOUND));

        return mapperFactory.getFriedIngredientMapper().deleteByFriedAndIngredient(friedDTO, ingredientDTO);
    }

    private FriedDTO privateFindFriedByName(final String friedName) {
        return Optional.ofNullable(friedName)
                .map(n -> mapperFactory.getFriedMapper().findByName(n))
                .orElse(null);
    }

    private IngredientDTO privateFindIngredientByName(final String ingredientName) {
        return Optional.ofNullable(ingredientName)
                .map(n -> mapperFactory.getIngredientMapper().findByName(n))
                .orElse(null);
    }
}
