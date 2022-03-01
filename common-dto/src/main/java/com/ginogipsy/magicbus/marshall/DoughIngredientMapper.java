package com.ginogipsy.magicbus.marshall;

import com.ginogipsy.magicbus.domain.Dough;
import com.ginogipsy.magicbus.domain.DoughIngredient;
import com.ginogipsy.magicbus.domain.Ingredient;
import com.ginogipsy.magicbus.dto.DoughDTO;
import com.ginogipsy.magicbus.dto.DoughIngredientDTO;
import com.ginogipsy.magicbus.dto.IngredientDTO;
import com.ginogipsy.magicbus.repository.DoughIngredientRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Component
public class DoughIngredientMapper {

    private final ModelMapper modelMapper;
    private final DoughIngredientRepository doughIngredientRepository;
    private final DoughMapper doughMapper;
    private final IngredientMapper ingredientMapper;

    public DoughIngredientMapper(ModelMapper modelMapper, DoughIngredientRepository doughIngredientRepository, DoughMapper doughMapper, IngredientMapper ingredientMapper) {
        this.modelMapper = modelMapper;
        this.doughIngredientRepository = doughIngredientRepository;
        this.doughMapper = doughMapper;
        this.ingredientMapper = ingredientMapper;
    }

    public DoughIngredient convertToEntity(final DoughIngredientDTO doughIngredientDTO) {
        return Optional.ofNullable(doughIngredientDTO)
                .map(di -> modelMapper.map(di, DoughIngredient.class))
                .orElse(null);
    }

    public DoughIngredientDTO convertToDTO(final DoughIngredient doughIngredient) {
        return Optional.ofNullable(doughIngredient)
                .map(di -> modelMapper.map(di, DoughIngredientDTO.class))
                .orElse(null);
    }

    public DoughIngredientDTO findByDoughAndIngredient(final DoughDTO doughDTO, final IngredientDTO ingredientDTO) {
        final Dough dough = takeDough(doughDTO);
        final Ingredient ingredient = takeIngredient(ingredientDTO);

        return Optional.ofNullable(dough)
                .filter(d -> Objects.nonNull(ingredient))
                .map(d -> doughIngredientRepository.findByDoughAndIngredient(d, ingredient))
                .map(this::convertToDTO)
                .orElse(null);
    }

    public DoughIngredientDTO save(final DoughIngredientDTO doughIngredientDTO) {
        log.info("Saving doughIngredient on db..");
        return Optional.ofNullable(doughIngredientDTO)
                .map(this::convertToEntity)
                .map(doughIngredientRepository::save)
                .map(this::convertToDTO)
                .orElse(null);
    }

    public List<DoughIngredientDTO> findByDough(final DoughDTO doughDTO) {
        final Dough dough = takeDough(doughDTO);
        log.info("Searching doughIngredient on db by dough..");
        return Optional.ofNullable(dough)
                .map(d -> doughIngredientRepository.findByDough(d)
                        .stream()
                        .map(this::convertToDTO)
                        .toList())
                .orElse(new ArrayList<>());
    }

    public List<DoughIngredientDTO> findByIngredient(final IngredientDTO ingredientDTO) {
        final Ingredient ingredient = takeIngredient(ingredientDTO);
        log.info("Searching doughIngredient on db by ingredient..");
        return Optional.ofNullable(ingredient)
                .map(i -> doughIngredientRepository.findByIngredient(i)
                        .stream()
                        .map(this::convertToDTO)
                        .toList())
                .orElse(new ArrayList<>());
    }

    private Dough takeDough(final DoughDTO doughDTO) {
        log.info("Verifying doughDTO..");
        return Optional.ofNullable(doughDTO)
                .map(doughMapper::convertToEntity)
                .orElse(null);
    }

    private Ingredient takeIngredient(final IngredientDTO ingredientDTO) {
        log.info("Verifying ingredientDTO..");
        return Optional.ofNullable(ingredientDTO)
                .map(ingredientMapper::convertToEntity)
                .orElse(null);
    }
}
