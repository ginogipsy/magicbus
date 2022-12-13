package com.ginogipsy.magicbus.marshall;


import com.ginogipsy.magicbus.domain.Ingredient;
import com.ginogipsy.magicbus.domain.Topping;
import com.ginogipsy.magicbus.domain.ToppingIngredient;
import com.ginogipsy.magicbus.dto.IngredientDTO;
import com.ginogipsy.magicbus.dto.ToppingDTO;
import com.ginogipsy.magicbus.dto.ToppingIngredientDTO;
import com.ginogipsy.magicbus.marshall.utils.ConvertMapperUtils;
import com.ginogipsy.magicbus.repository.ToppingIngredientRepository;
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
public class ToppingIngredientMapper {

    private final ModelMapper modelMapper;
    private final ToppingIngredientRepository toppingIngredientRepository;
    private final ConvertMapperUtils convertMapperUtils;

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

    public Optional<ToppingIngredient> convertToEntity(final Optional<ToppingIngredientDTO> toppingIngredientDTO) {
        return Optional.ofNullable(toppingIngredientDTO)
                .map(t -> modelMapper.map(t, ToppingIngredient.class));
    }

    public Optional<ToppingIngredientDTO> convertToDTO(final Optional<ToppingIngredient> toppingIngredient) {
        return Optional.ofNullable(toppingIngredient)
                .map(t -> modelMapper.map(t, ToppingIngredientDTO.class));
    }

    public Optional<ToppingIngredientDTO> save(final ToppingIngredientDTO toppingIngredientDTO) {
        log.info("ToppingIngredientMapper - save() -> Saving topping/ingredient on db..");
        return Optional.ofNullable(toppingIngredientDTO)
                .map(this::convertToEntity)
                .map(toppingIngredientRepository::save)
                .map(this::convertToDTO);
    }

    public List<ToppingIngredientDTO> findByTopping(final ToppingDTO toppingDTO) {
        final Optional<Topping> topping = convertMapperUtils.convertTopping(toppingDTO);
        log.info("ToppingIngredientMapper - findByTopping() -> Searching topping/ingredient list with topping named {} ..", topping.isPresent() ? topping.get().getName() : "");
        return topping.map(t -> toppingIngredientRepository.findByTopping(t)
                        .stream()
                        .map(this::convertToDTO)
                        .toList())
                .orElse(new ArrayList<>());
    }

    public List<ToppingIngredientDTO> findByIngredient(final IngredientDTO ingredientDTO) {
        final Optional<Ingredient> ingredient = convertMapperUtils.convertIngredient(ingredientDTO);
        log.info("ToppingIngredientMapper - findByIngredient() -> Searching topping/ingredient list with topping named {} ..", ingredient.isPresent() ? ingredient.get().getName() : "");
        return ingredient.map(i -> toppingIngredientRepository.findByIngredient(i)
                        .stream()
                        .map(this::convertToDTO)
                        .toList())
                .orElse(new ArrayList<>());
    }

    public Optional<ToppingIngredientDTO> findByToppingAndIngredient(final ToppingDTO toppingDTO, final IngredientDTO ingredientDTO) {
        final Optional<Topping> topping = convertMapperUtils.convertTopping(toppingDTO);
        final Optional<Ingredient> ingredient = convertMapperUtils.convertIngredient(ingredientDTO);
        log.info("ToppingIngredientMapper - findByToppingAndIngredient() -> Deleting topping/ingredient with topping named {} and ingredient named '{}' ..", topping.isPresent() ? topping.get().getName() : "", ingredient.isPresent() ? ingredient.get().getName() : "");

        return topping.filter(t -> ingredient.isPresent())
                .map(t -> toppingIngredientRepository.findByToppingAndIngredient(t, ingredient.get()))
                .flatMap(this::convertToDTO);
    }

    public String deleteByToppingAndIngredient(final ToppingDTO toppingDTO, final IngredientDTO ingredientDTO) {
        final Optional<Topping> topping = convertMapperUtils.convertTopping(toppingDTO);
        final Optional<Ingredient> ingredient = convertMapperUtils.convertIngredient(ingredientDTO);
        log.info("ToppingIngredientMapper - deleteByToppingAndIngredient() -> Deleting topping/ingredient with topping named {} and ingredient named '{}' ..", topping.isPresent() ? topping.get().getName() : "", ingredient.isPresent() ? ingredient.get().getName() : "");

        if (topping.isPresent()) {
            if (ingredient.isPresent()) {
                toppingIngredientRepository.deleteByToppingAndIngredient(topping.get(), ingredient.get());
                log.info("deleted!");
                return "deleted";
            }
            log.warn("ToppingIngredientMapper - deleteByToppingAndIngredient() -> ingredient is null!");
            return "no deleted!";
        }
        log.warn("ToppingIngredientMapper - deleteByToppingAndIngredient() -> topping is null!");
        return "no deleted!";
    }
}
