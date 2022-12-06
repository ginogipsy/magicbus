package com.ginogipsy.magicbus.marshall;

import com.ginogipsy.magicbus.domain.Fried;
import com.ginogipsy.magicbus.domain.FriedIngredient;
import com.ginogipsy.magicbus.domain.Ingredient;
import com.ginogipsy.magicbus.dto.FriedDTO;
import com.ginogipsy.magicbus.dto.FriedIngredientDTO;
import com.ginogipsy.magicbus.dto.IngredientDTO;
import com.ginogipsy.magicbus.repository.FriedIngredientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @author ginogipsy
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class FriedIngredientMapper {

    private final ModelMapper modelMapper;
    private final FriedIngredientRepository friedIngredientRepository;
    private final IngredientMapper ingredientMapper;
    private final FriedMapper friedMapper;

    public FriedIngredient convertToEntity(final FriedIngredientDTO friedIngredientDTO) {
        return Optional.ofNullable(friedIngredientDTO)
                .map(fi -> modelMapper.map(fi, FriedIngredient.class))
                .orElse(null);
    }

    public FriedIngredientDTO convertToDTO(final FriedIngredient friedIngredient) {
        return Optional.ofNullable(friedIngredient)
                .map(fi -> modelMapper.map(fi, FriedIngredientDTO.class))
                .orElse(null);
    }

    public Optional<FriedIngredient> convertToEntity(final Optional<FriedIngredientDTO> friedIngredientDTO) {
        return Optional.ofNullable(friedIngredientDTO)
                .map(fi -> modelMapper.map(fi, FriedIngredient.class));
    }

    public Optional<FriedIngredientDTO> convertToDTO(final Optional<FriedIngredient> friedIngredient) {
        return Optional.ofNullable(friedIngredient)
                .map(fi -> modelMapper.map(fi, FriedIngredientDTO.class));
    }

    public Optional<FriedIngredientDTO> findByFriedAndIngredient(final FriedDTO friedDTO, final IngredientDTO ingredientDTO) {
        final Optional<Fried> fried = takeFried(friedDTO);
        final Optional<Ingredient> ingredient = takeIngredient(ingredientDTO);
        log.info("FriedIngredientMapper - findByFriedAndIngredient() -> Searching fried/ingredient with fried named {} and ingredient named '{}' ..",fried.isPresent() ? fried.get().getName() : "", ingredient.isPresent() ? ingredient.get().getName() : "");
        return fried.filter(f -> ingredient.isPresent())
                .map(f -> friedIngredientRepository.findByFriedAndIngredient(f, ingredient.get()))
                .flatMap(this::convertToDTO);
    }

    public Optional<FriedIngredientDTO> save(final FriedIngredientDTO friedIngredientDTO) {
        log.info("FriedIngredientMapper - findByFried() -> Saving friedIngredient on db..");
        return Optional.ofNullable(friedIngredientDTO)
                .map(this::convertToEntity)
                .map(friedIngredientRepository::save)
                .map(this::convertToDTO);
    }

    public List<FriedIngredientDTO> findByFried(final FriedDTO friedDTO) {
        final Optional<Fried> fried = takeFried(friedDTO);
        log.info("FriedIngredientMapper - findByFried() -> Searching fried/ingredient list with dough named {} ..", fried.isPresent() ? fried.get().getName() : "");
        return fried.map(f -> friedIngredientRepository.findByFried(f)
                        .stream()
                        .map(this::convertToDTO)
                        .toList())
                .orElse(new ArrayList<>());
    }

    public List<FriedIngredientDTO> findByIngredient(final IngredientDTO ingredientDTO) {
        final Optional<Ingredient> ingredient = takeIngredient(ingredientDTO);
        log.info("FriedIngredientMapper - findByIngredient() -> Searching fried/ingredient list with fried named {} ..", ingredient.isPresent() ? ingredient.get().getName() : "");
        return ingredient.map(i -> friedIngredientRepository.findByIngredient(i)
                        .stream()
                        .map(this::convertToDTO)
                        .toList())
                .orElse(new ArrayList<>());
    }

    public String deleteByFriedAndIngredient(final FriedDTO friedDTO, final IngredientDTO ingredientDTO) {
        final Optional<Fried> fried = takeFried(friedDTO);
        final Optional<Ingredient> ingredient = takeIngredient(ingredientDTO);
        log.info("FriedIngredientMapper - deleteByFriedAndIngredient() -> Deleting fried/ingredient with dough named {} and ingredient named '{}' ..",fried.isPresent() ? fried.get().getName() : "", ingredient.isPresent() ? ingredient.get().getName() : "");
        if (fried.isPresent()) {
            if(ingredient.isPresent()) {
                friedIngredientRepository.deleteByFriedAndIngredient(fried.get(), ingredient.get());
                log.info("FriedIngredientMapper - deleteByFriedAndIngredient() -> deleted!");
                return "deleted";
            }
            log.warn("FriedIngredientMapper - deleteByFriedAndIngredient() -> ingredient is null!");
            return "no deleted!";
        }
        log.warn("FriedIngredientMapper - deleteByFriedAndIngredient() -> fried is null!");
        return "no deleted!";
    }

    private Optional<Fried> takeFried(final FriedDTO friedDTO) {
        log.info("FriedIngredientMapper - takeFried() -> Verifying friedDTO..");
        return Optional.ofNullable(friedDTO)
                .map(friedMapper::convertToEntity);
    }

    private Optional<Ingredient> takeIngredient(final IngredientDTO ingredientDTO) {
        log.info("FriedIngredientMapper - takeIngredient() -> Verifying ingredientDTO..");
        return Optional.ofNullable(ingredientDTO)
                .map(ingredientMapper::convertToEntity);
    }
}
