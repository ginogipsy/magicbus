package com.ginogipsy.magicbus.service;

import com.ginogipsy.magicbus.customexception.controller.DataNotCorrectException;
import com.ginogipsy.magicbus.customexception.notfound.ObjectNotFoundException;
import com.ginogipsy.magicbus.dto.DoughDTO;
import com.ginogipsy.magicbus.dto.DoughIngredientDTO;
import com.ginogipsy.magicbus.dto.IngredientDTO;
import com.ginogipsy.magicbus.marshall.MapperFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class DoughIngredientServiceImpl implements DoughIngredientService {

    private final MapperFactory mapperFactory;

    public DoughIngredientServiceImpl(MapperFactory mapperFactory) {
        this.mapperFactory = mapperFactory;
    }


    @Override
    public DoughIngredientDTO save(final String doughName, final String ingredientName) {
        log.info("Checking if this dough is present..");
        final DoughDTO doughDTO = Optional.ofNullable(privateFindDoughByName(doughName))
                .orElseThrow(() -> new ObjectNotFoundException("Dough is not present!"));
        log.info("Checking if this ingredient is present..");
        final IngredientDTO ingredientDTO = Optional.ofNullable(privateFindIngredientByName(ingredientName))
                .orElseThrow(() -> new ObjectNotFoundException("Dough is not present!"));

        if (Optional.ofNullable(mapperFactory.getDoughIngredientMapper().findByDoughAndIngredient(doughDTO, ingredientDTO)).isPresent()) {
            log.error("It is already present this ingredient " + ingredientName + " for this dough " + doughName + "!");
            throw new DataNotCorrectException("It is already present this ingredient " + ingredientName + " for this dough " + doughName + "!");
        }
        log.info("Saving the doughIngredient..");
        final DoughIngredientDTO doughIngredientDTO = new DoughIngredientDTO();
        doughIngredientDTO.setIngredient(ingredientDTO);
        doughIngredientDTO.setDough(doughDTO);
        return mapperFactory.getDoughIngredientMapper().save(doughIngredientDTO);
    }

    @Override
    public List<IngredientDTO> findByDough(final DoughDTO doughDTO) {
        log.info("Checking if this dough is present..");
        if (Optional.ofNullable(doughDTO).isEmpty() || Optional.ofNullable(privateFindDoughByName(doughDTO.getName())).isEmpty()) {
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
    public List<DoughDTO> findByIngredient(final IngredientDTO ingredientDTO) {
        log.info("Checking if this ingredient is present..");
        if (Optional.ofNullable(ingredientDTO).isEmpty() || Optional.ofNullable(privateFindDoughByName(ingredientDTO.getName())).isEmpty()) {
            log.warn("this dough doesn't exists..");
            return new ArrayList<>();
        }
        log.info("Start searching doughs for this ingredient..");
        return Optional.ofNullable(mapperFactory.getDoughIngredientMapper().findByIngredient(ingredientDTO))
                .map(dis -> dis.stream()
                        .map(DoughIngredientDTO::getDough)
                        .toList())
                .orElse(new ArrayList<>());
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
