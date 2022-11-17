package com.ginogipsy.magicbus.service;

import com.ginogipsy.magicbus.dto.DoughDTO;
import com.ginogipsy.magicbus.dto.DoughIngredientDTO;
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
public class DoughIngredientServiceImpl implements DoughIngredientService {

    private final MapperFactory mapperFactory;

    public DoughIngredientServiceImpl(MapperFactory mapperFactory) {
        this.mapperFactory = mapperFactory;
    }


    @Override
    public DoughIngredientDTO insertIngredient(final String doughName, final String ingredientName) {
        log.info("Checking if this dough is present..");
        final DoughDTO doughDTO = Optional.ofNullable(privateFindDoughByName(doughName))
                .orElseThrow(() -> new MagicbusException(DOUGH_NOT_FOUND));
        log.info("Checking if this ingredient is present..");
        final IngredientDTO ingredientDTO = Optional.ofNullable(privateFindIngredientByName(ingredientName))
                .orElseThrow(() -> new MagicbusException(INGREDIENT_NOT_FOUND));

        if (Optional.ofNullable(mapperFactory.getDoughIngredientMapper().findByDoughAndIngredient(doughDTO, ingredientDTO)).isPresent()) {
            log.error("It is already present this ingredient " + ingredientName + " for this dough " + doughName + "!");
            throw new MagicbusException(INGREDIENT_DOUGH_IS_ALREADY_PRESENT, "It is already present this ingredient " + ingredientName + " for this dough " + doughName + "!");
        }
        log.info("Saving the doughIngredient..");
        final DoughIngredientDTO doughIngredientDTO = new DoughIngredientDTO();
        doughIngredientDTO.setIngredient(ingredientDTO);
        doughIngredientDTO.setDough(doughDTO);
        return mapperFactory.getDoughIngredientMapper().save(doughIngredientDTO);
    }

    @Override
    public List<String> insertIngredients(final String doughName, final List<String> ingredientList) {
        log.info("Checking if this dough is present..");
        final DoughDTO doughDTO = Optional.ofNullable(privateFindDoughByName(doughName))
                .orElseThrow(() -> new MagicbusException(DOUGH_NOT_FOUND));
        final List<String> ingredientsAdded = new ArrayList<>();
        for (final String ingredientName :
                ingredientList) {
            log.info("Checking if this ingredient is present..");
            final IngredientDTO ingredientDTO = privateFindIngredientByName(ingredientName);
            if (Optional.ofNullable(mapperFactory.getDoughIngredientMapper().findByDoughAndIngredient(doughDTO, ingredientDTO)).isPresent()) {
                final String error = "It is already present this ingredient \"" + ingredientName + "\" for this dough \"" + doughName + "\"!";
                log.error(error);
                ingredientsAdded.add(error);
                continue;
            }
            log.info("Saving the doughIngredient..");
            final DoughIngredientDTO doughIngredientDTO = new DoughIngredientDTO();
            doughIngredientDTO.setIngredient(ingredientDTO);
            doughIngredientDTO.setDough(doughDTO);
            mapperFactory.getDoughIngredientMapper().save(doughIngredientDTO);
            ingredientsAdded.add(doughIngredientDTO.getIngredient().getName());
        }
        return ingredientsAdded;
    }

    @Override
    public List<IngredientDTO> findByDough(final String doughName) {
        log.info("Checking if this dough is present..");
        final DoughDTO doughDTO = privateFindDoughByName(doughName);
        if (Optional.ofNullable(doughDTO).isEmpty()) {
            log.warn("this dough doesn't exists..");
            return new ArrayList<>();
        }
        log.info("Start searching ingredients for this dough..");
        return Optional.ofNullable(mapperFactory.getDoughIngredientMapper().findByDough(doughDTO))
                .map(i -> i.stream()
                        .map(DoughIngredientDTO::getIngredient)
                        .toList())
                .orElse(new ArrayList<>());
    }

    @Override
    public List<DoughDTO> findByIngredient(final String ingredientName) {
        log.info("Checking if this ingredient is present..");
        final IngredientDTO ingredientDTO = privateFindIngredientByName(ingredientName);
        if (Optional.ofNullable(ingredientDTO).isEmpty()) {
            log.warn("this ingredient doesn't exists..");
            return new ArrayList<>();
        }
        log.info("Start searching doughs for this ingredient..");
        return Optional.ofNullable(mapperFactory.getDoughIngredientMapper().findByIngredient(ingredientDTO))
                .map(dis -> dis.stream()
                        .map(DoughIngredientDTO::getDough)
                        .toList())
                .orElse(new ArrayList<>());
    }

    @Override
    public String deleteByDoughAndIngredient(final String doughName, final String ingredientName) {
        log.info("Checking if this dough is present..");
        final DoughDTO doughDTO = Optional.ofNullable(privateFindDoughByName(doughName))
                .orElseThrow(() -> new MagicbusException(DOUGH_NOT_FOUND));
        log.info("Checking if this ingredient is present..");
        final IngredientDTO ingredientDTO = Optional.ofNullable(privateFindIngredientByName(ingredientName))
                .orElseThrow(() -> new MagicbusException(INGREDIENT_NOT_FOUND));

        return mapperFactory.getDoughIngredientMapper().deleteByDoughAndIngredient(doughDTO, ingredientDTO);
    }

    private DoughDTO privateFindDoughByName(final String doughName) {
        return Optional.ofNullable(doughName)
                .map(n -> mapperFactory.getDoughMapper().findByName(n))
                .orElse(null);
    }

    private IngredientDTO privateFindIngredientByName(final String ingredientName) {
        return Optional.ofNullable(ingredientName)
                .map(n -> mapperFactory.getIngredientMapper().findByName(n))
                .orElse(null);
    }
}
