package com.ginogipsy.magicbus.service;

import com.ginogipsy.magicbus.component.StringUtility;
import com.ginogipsy.magicbus.dto.IngredientDTO;
import com.ginogipsy.magicbus.exceptionhandler.BeErrorCodeEnum;
import com.ginogipsy.magicbus.exceptionhandler.MagicbusException;
import com.ginogipsy.magicbus.marshall.MapperFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.ginogipsy.magicbus.exceptionhandler.BeErrorCodeEnum.*;

/**
 * @author ginogipsy
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class IngredientServiceImpl implements IngredientService {

    private final MapperFactory mapperFactory;
    private final StringUtility stringUtility;

    @Override
    public IngredientDTO findByName(final String name) {
        return privateFindByName(name)
                .orElseThrow(() -> new MagicbusException(INGREDIENT_NOT_FOUND, "Ingredient " + name + " not found!"));
    }

    @Override
    public List<IngredientDTO> findByNameContains(final String partialName) {
        return mapperFactory.getIngredientMapper().findByNameContains(partialName);
    }

    @Override
    public IngredientDTO insert(final IngredientDTO ingredientDTO) {
        log.info("IngredientServiceImpl - insert() -> Checking if ingredient is null..");
        final String ingredientName = Optional.ofNullable(ingredientDTO)
                .map(IngredientDTO::getName)
                .orElseThrow(() -> new MagicbusException(INGREDIENT_IS_NULL));

        log.info("IngredientServiceImpl - insert() -> If present inserting brand in the name ..");
        Optional.ofNullable(ingredientDTO.getBrand())
                .ifPresent(brand -> ingredientDTO.setName(addBrandNameInIngredientName(ingredientDTO.getName(), brand.getName())));

        if (privateFindByName(ingredientName).isPresent()) {
            log.error("IngredientServiceImpl - insert() -> It is already present an ingredient called {}!", ingredientName);
            throw new MagicbusException(INGREDIENT_IS_ALREADY_PRESENT, "It is already present an ingredient called " + ingredientName + "!");
        }

        privateFormatIngredient(ingredientDTO);

        log.info("IngredientServiceImpl - insert() -> Saving the ingredient..");
        return mapperFactory.getIngredientMapper()
                .save(ingredientDTO)
                .orElseThrow(() -> new MagicbusException(BeErrorCodeEnum.SAVE_FAILED));
    }

    private Optional<IngredientDTO> privateFindByName(final String ingredientName) {
        stringUtility.formatAllLower(ingredientName);
        log.info("IngredientServiceImpl - privateFindByName() -> Finding ingredient named '{}'..", ingredientName);
        return Optional.ofNullable(ingredientName)
                .flatMap(n -> mapperFactory.getIngredientMapper().findByName(n));
    }
    private void privateFormatIngredient(final IngredientDTO ingredientDTO) {
        log.info("IngredientServiceImpl - privateFormatIngredient() -> Formatting name and description..");

        if(ingredientDTO == null) {
            log.warn("IngredientServiceImpl - privateFormatIngredient() -> ingredientDTO is null!");
            return;
        }

        Optional.ofNullable(ingredientDTO.getName())
                .ifPresent(n -> ingredientDTO.setName(stringUtility.formatAllLower(n)));
        Optional.ofNullable(ingredientDTO.getDescription())
                .ifPresent(n -> ingredientDTO.setDescription(stringUtility.formatAllLower(n)));
    }

    private String addBrandNameInIngredientName(final String ingredientName, final String brandName) {
        if(ingredientName == null) {
            return null;
        }

        final String ingName = stringUtility.formatAllLower(ingredientName);

        if(brandName == null) {
            return ingName;
        }

        final String brName =stringUtility.formatAllLower(brandName);

        if (ingName.contains(brName)) {
            return ingName;
        }

        return ingName.concat(" \"" + brName + "\"");
    }
}
