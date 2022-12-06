package com.ginogipsy.magicbus.marshall;


import com.ginogipsy.magicbus.domain.Ingredient;
import com.ginogipsy.magicbus.dto.IngredientDTO;
import com.ginogipsy.magicbus.repository.IngredientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author ginogipsy
 */

@Slf4j
@Component
@RequiredArgsConstructor
public class IngredientMapper {

    private final ModelMapper modelMapper;
    private final IngredientRepository ingredientRepository;

    public Ingredient convertToEntity(final IngredientDTO ingredientDTO){
        return Optional.ofNullable(ingredientDTO)
                .map(i -> modelMapper.map(i, Ingredient.class))
                .orElse(null);
    }

    public IngredientDTO convertToDTO(final Ingredient ingredient) {
        return Optional.ofNullable(ingredient)
                .map(i -> modelMapper.map(i, IngredientDTO.class))
                .orElse(null);
    }

    public Optional<Ingredient> convertToEntity(final Optional<IngredientDTO> ingredientDTO){
        return Optional.ofNullable(ingredientDTO)
                .map(i -> modelMapper.map(i, Ingredient.class));
    }

    public Optional<IngredientDTO> convertToDTO(final Optional<Ingredient> ingredient) {
        return Optional.ofNullable(ingredient)
                .map(i -> modelMapper.map(i, IngredientDTO.class));
    }

    public Optional<IngredientDTO> findByName(final String name) {
        log.info("IngredientMapper - findByName() -> Searching ingredient where name is '{}' ..", name);
        return Optional.ofNullable(name)
                .flatMap(ingredientRepository::findByName)
                .map(this::convertToDTO);
    }

    public Optional<IngredientDTO> save(final IngredientDTO ingredientDTO) {
        log.info("IngredientMapper - save() -> Saving ingredient on db..");
        return Optional.ofNullable(ingredientDTO)
                .map(this::convertToEntity)
                .map(ingredientRepository::save)
                .map(this::convertToDTO);
    }

    public List<IngredientDTO> findByNameContains(final String name) {
        log.info("IngredientMapper - findByNameContains() -> Searching list of ingredients that their name contains {}..", name);
        return Optional.ofNullable(name)
                .map(n -> ingredientRepository.findByNameContains(n)
                        .stream()
                        .map(this::convertToDTO)
                        .toList())
                .orElse(new ArrayList<>());
    }
}
