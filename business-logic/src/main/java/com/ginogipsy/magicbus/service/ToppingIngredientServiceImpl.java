package com.ginogipsy.magicbus.service;

import com.ginogipsy.magicbus.customexception.controller.DataNotCorrectException;
import com.ginogipsy.magicbus.customexception.notfound.ObjectNotFoundException;
import com.ginogipsy.magicbus.dto.IngredientDTO;
import com.ginogipsy.magicbus.dto.ToppingDTO;
import com.ginogipsy.magicbus.dto.ToppingIngredientDTO;
import com.ginogipsy.magicbus.marshall.MapperFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ToppingIngredientServiceImpl implements ToppingIngredientService {

    private final MapperFactory mapperFactory;

    public ToppingIngredientServiceImpl(MapperFactory mapperFactory) {
        this.mapperFactory = mapperFactory;
    }

    @Override
    public ToppingIngredientDTO save(final String toppingName, final String ingredientName) {
        log.info("Checking if this topping is present..");
        final ToppingDTO toppingDTO = Optional.ofNullable(privateFindToppingByName(toppingName))
                .orElseThrow(() -> new ObjectNotFoundException("Topping is not present!"));

        log.info("Checking if this ingredient is present..");
        final IngredientDTO ingredientDTO = Optional.ofNullable(privateFindIngredientByName(ingredientName))
                .orElseThrow(() -> new ObjectNotFoundException("Ingredient is not present!"));

        if (Optional.ofNullable(mapperFactory.getToppingIngredientMapper().findByToppingAndIngredient(toppingDTO, ingredientDTO)).isPresent()) {
            log.error("It is already present this ingredient " + ingredientName + " for this topping " + toppingName + "!");
            throw new DataNotCorrectException("It is already present this ingredient " + ingredientName + " for this topping " + toppingName + "!");
        }

        log.info("Saving the toppingIngredient..");
        final ToppingIngredientDTO toppingIngredientDTO = new ToppingIngredientDTO();
        toppingIngredientDTO.setIngredient(ingredientDTO);
        toppingIngredientDTO.setTopping(toppingDTO);
        return mapperFactory.getToppingIngredientMapper().save(toppingIngredientDTO);
    }

    @Override
    public List<IngredientDTO> findByTopping(final String toppingName) {
        log.info("Checking if this topping is present..");
        final ToppingDTO toppingDTO = privateFindToppingByName(toppingName);
        if (Optional.ofNullable(toppingDTO).isEmpty()) {
            log.warn("this topping doesn't exists..");
            return new ArrayList<>();
        }

        log.info("Start searching ingredients for this dough..");
        return Optional.ofNullable(mapperFactory.getToppingIngredientMapper().findByTopping(toppingDTO))
                .map(l -> l.stream()
                        .map(ToppingIngredientDTO::getIngredient)
                        .toList())
                .orElse(new ArrayList<>());
    }

    @Override
    public List<ToppingDTO> findByIngredient(final String ingredientName) {
        log.info("Checking if this ingredient is present..");
        final IngredientDTO ingredientDTO = privateFindIngredientByName(ingredientName);
        if (Optional.ofNullable(ingredientDTO).isEmpty()) {
            log.warn("this ingredient doesn't exists..");
            return new ArrayList<>();
        }
        log.info("Start searching toppings for this ingredient..");
        return Optional.ofNullable(mapperFactory.getToppingIngredientMapper().findByIngredient(ingredientDTO))
                .map(l -> l.stream()
                        .map(ToppingIngredientDTO::getTopping)
                        .toList())
                .orElse(new ArrayList<>());
    }

    @Override
    public String deleteByToppingAndIngredient(final String toppingName, final String ingredientName) {
        log.info("Checking if this topping is present..");
        final ToppingDTO toppingDTO = Optional.ofNullable(privateFindToppingByName(toppingName))
                .orElseThrow(() -> new ObjectNotFoundException("Topping is not present!"));

        log.info("Checking if this ingredient is present..");
        final IngredientDTO ingredientDTO = Optional.ofNullable(privateFindIngredientByName(ingredientName))
                .orElseThrow(() -> new ObjectNotFoundException("Ingredient is not present!"));

        return mapperFactory.getToppingIngredientMapper().deleteByToppingAndIngredient(toppingDTO, ingredientDTO);
    }

    private ToppingDTO privateFindToppingByName(final String toppingName) {
        return Optional.ofNullable(toppingName)
                .map(n -> mapperFactory.getToppingMapper().findByName(n))
                .orElse(null);
    }

    private IngredientDTO privateFindIngredientByName(final String ingredientName) {
        return Optional.ofNullable(ingredientName)
                .map(n -> mapperFactory.getIngredientMapper().findByName(n))
                .orElse(null);
    }
}
