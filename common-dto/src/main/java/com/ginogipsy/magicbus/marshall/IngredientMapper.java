package com.ginogipsy.magicbus.marshall;


import com.ginogipsy.magicbus.domain.Ingredient;
import com.ginogipsy.magicbus.dto.IngredientDTO;
import com.ginogipsy.magicbus.repository.IngredientRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class IngredientMapper {

    private final ModelMapper modelMapper;
    private final IngredientRepository ingredientRepository;

    public IngredientMapper(ModelMapper modelMapper, IngredientRepository ingredientRepository) {
        this.modelMapper = modelMapper;
        this.ingredientRepository = ingredientRepository;
    }

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

    public IngredientDTO findByName(final String name) {
        log.info("Searching ingredient named " + name + " on db..");
        return Optional.ofNullable(name)
                .map(ingredientRepository::findByName)
                .map(this::convertToDTO)
                .orElse(null);
    }

    public IngredientDTO save(final IngredientDTO ingredientDTO) {
        log.info("Saving ingredient on db..");
        return Optional.ofNullable(ingredientDTO)
                .map(this::convertToEntity)
                .map(ingredientRepository::save)
                .map(this::convertToDTO)
                .orElse(null);
    }

    public List<IngredientDTO> findByNameContains(final String name) {
        log.info("Searching list of ingredients that their name contains " + name + "..");
        return Optional.ofNullable(name)
                .map(n -> ingredientRepository.findByNameContains(n)
                        .stream()
                        .map(this::convertToDTO)
                        .toList())
                .orElse(new ArrayList<>());
    }
}
