package com.ginogipsy.magicbus.marshall;


import com.ginogipsy.magicbus.domain.Ingredient;
import com.ginogipsy.magicbus.domain.Topping;
import com.ginogipsy.magicbus.domain.ToppingIngredient;
import com.ginogipsy.magicbus.dto.IngredientDTO;
import com.ginogipsy.magicbus.dto.ToppingDTO;
import com.ginogipsy.magicbus.dto.ToppingIngredientDTO;
import com.ginogipsy.magicbus.repository.ToppingIngredientRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Component
public class ToppingIngredientMapper {

    private final ModelMapper modelMapper;
    private final ToppingIngredientRepository toppingIngredientRepository;
    private final ToppingMapper toppingMapper;
    private final IngredientMapper ingredientMapper;

    public ToppingIngredientMapper(ModelMapper modelMapper, ToppingIngredientRepository toppingIngredientRepository, ToppingMapper toppingMapper, IngredientMapper ingredientMapper) {
        this.modelMapper = modelMapper;
        this.toppingIngredientRepository = toppingIngredientRepository;
        this.toppingMapper = toppingMapper;
        this.ingredientMapper = ingredientMapper;
    }

    public ToppingIngredient convertToEntity(final ToppingIngredientDTO toppingIngredientDTO) {
        return Optional.ofNullable(toppingIngredientDTO)
                .map(t -> modelMapper.map(t, ToppingIngredient.class))
                .orElse(null);
    }

    public ToppingIngredientDTO convertToDTO(final ToppingIngredient toppingIngredient) {
        return Optional.ofNullable(toppingIngredient)
                .map(t -> modelMapper.map(t, ToppingIngredientDTO.class))
                .orElse(null);
    }

    public ToppingIngredientDTO save(final ToppingIngredientDTO toppingIngredientDTO) {
        log.info("Saving toppingIngredient on db..");
        return Optional.ofNullable(toppingIngredientDTO)
                .map(this::convertToEntity)
                .map(toppingIngredientRepository::save)
                .map(this::convertToDTO)
                .orElse(null);
    }

    public List<ToppingIngredientDTO> findByTopping(final ToppingDTO toppingDTO) {
        final Topping topping = takeTopping(toppingDTO);
        log.info("Searching toppingIngredient on db by dough..");
        return Optional.ofNullable(topping)
                .map(t -> toppingIngredientRepository.findByTopping(t)
                        .stream()
                        .map(this::convertToDTO)
                        .toList())
                .orElse(new ArrayList<>());
    }

    public List<ToppingIngredientDTO> findByIngredient(final IngredientDTO ingredientDTO) {
        final Ingredient ingredient = takeIngredient(ingredientDTO);
        log.info("Searching toppingIngredient on db by ingredient..");
        return Optional.ofNullable(ingredient)
                .map(i -> toppingIngredientRepository.findByIngredient(i)
                        .stream()
                        .map(this::convertToDTO)
                        .toList())
                .orElse(new ArrayList<>());
    }

    public ToppingIngredientDTO findByToppingAndIngredient(final ToppingDTO toppingDTO, final IngredientDTO ingredientDTO) {
        final Topping topping = takeTopping(toppingDTO);
        final Ingredient ingredient = takeIngredient(ingredientDTO);

        return Optional.ofNullable(topping)
                .filter(t -> Objects.nonNull(ingredient))
                .map(t -> toppingIngredientRepository.findByToppingAndIngredient(t, ingredient))
                .map(this::convertToDTO)
                .orElse(null);
    }

    private Topping takeTopping(final ToppingDTO toppingDTO) {
        log.info("Verifying toppingDTO..");
        return Optional.ofNullable(toppingDTO)
                .map(toppingMapper::convertToEntity)
                .orElse(null);
    }


    private Ingredient takeIngredient(final IngredientDTO ingredientDTO) {
        log.info("Verifying ingredientDTO..");
        return Optional.ofNullable(ingredientDTO)
                .map(ingredientMapper::convertToEntity)
                .orElse(null);
    }
}
