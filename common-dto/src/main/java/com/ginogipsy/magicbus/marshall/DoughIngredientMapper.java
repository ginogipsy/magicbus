package com.ginogipsy.magicbus.marshall;

import com.ginogipsy.magicbus.domain.Dough;
import com.ginogipsy.magicbus.domain.DoughIngredient;
import com.ginogipsy.magicbus.domain.Fried;
import com.ginogipsy.magicbus.domain.Ingredient;
import com.ginogipsy.magicbus.dto.DoughDTO;
import com.ginogipsy.magicbus.dto.DoughIngredientDTO;
import com.ginogipsy.magicbus.dto.IngredientDTO;
import com.ginogipsy.magicbus.marshall.utils.ConvertMapperUtils;
import com.ginogipsy.magicbus.repository.DoughIngredientRepository;
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
public class DoughIngredientMapper {

    private final ModelMapper modelMapper;
    private final DoughIngredientRepository doughIngredientRepository;
    private final ConvertMapperUtils convertMapperUtils;

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

    public Optional<DoughIngredient> convertToEntity(final Optional<DoughIngredientDTO> doughIngredientDTO) {
        return Optional.ofNullable(doughIngredientDTO)
                .map(di -> modelMapper.map(di, DoughIngredient.class));
    }

    public Optional<DoughIngredientDTO> convertToDTO(final Optional<DoughIngredient> doughIngredient) {
        return Optional.ofNullable(doughIngredient)
                .map(di -> modelMapper.map(di, DoughIngredientDTO.class));
    }

    public Optional<DoughIngredientDTO> findByDoughAndIngredient(final DoughDTO doughDTO, final IngredientDTO ingredientDTO) {
        final Optional<Dough> dough = convertMapperUtils.convertDough(doughDTO);
        final Optional<Ingredient> ingredient = convertMapperUtils.convertIngredient(ingredientDTO);
        log.info("DoughIngredientMapper - findByDoughAndIngredient() -> Searching dough/ingredient with dough named {} and ingredient named '{}' ..", dough.isPresent() ? dough.get().getName() : "", ingredient.isPresent() ? ingredient.get().getName() : "");
        return dough.filter(d -> ingredient.isPresent())
                .map(d -> doughIngredientRepository.findByDoughAndIngredient(d, ingredient.get()))
                .flatMap(this::convertToDTO);
    }

    public Optional<DoughIngredientDTO> save(final DoughIngredientDTO doughIngredientDTO) {
        log.info("DoughIngredientMapper - save() -> Saving doughIngredient on db..");
        return Optional.ofNullable(doughIngredientDTO)
                .map(this::convertToEntity)
                .map(doughIngredientRepository::save)
                .map(this::convertToDTO);
    }

    public List<DoughIngredientDTO> findByDough(final DoughDTO doughDTO) {
        final Optional<Dough> dough = convertMapperUtils.convertDough(doughDTO);
        log.info("DoughIngredientMapper - findByDough() -> Searching dough/ingredient list with dough named {} ..", dough.isPresent() ? dough.get().getName() : "");
        return dough.map(d -> doughIngredientRepository.findByDough(d)
                        .stream()
                        .map(this::convertToDTO)
                        .toList())
                .orElse(new ArrayList<>());
    }

    public List<DoughIngredientDTO> findByIngredient(final IngredientDTO ingredientDTO) {
        final Optional<Ingredient> ingredient = convertMapperUtils.convertIngredient(ingredientDTO);
        log.info("DoughIngredientMapper - findByIngredient() -> Searching dough/ingredient list with dough named {} ..", ingredient.isPresent() ? ingredient.get().getName() : "");
        return ingredient.map(i -> doughIngredientRepository.findByIngredient(i)
                        .stream()
                        .map(this::convertToDTO)
                        .toList())
                .orElse(new ArrayList<>());
    }

    public String deleteByDoughAndIngredient(final DoughDTO doughDTO, final IngredientDTO ingredientDTO) {
        final Optional<Dough> dough = convertMapperUtils.convertDough(doughDTO);
        final Optional<Ingredient> ingredient = convertMapperUtils.convertIngredient(ingredientDTO);
        log.info("DoughIngredientMapper - deleteByDoughAndIngredient() -> Deleting dough/ingredient with dough named {} and ingredient named '{}' ..",dough.isPresent() ? dough.get().getName() : "", ingredient.isPresent() ? ingredient.get().getName() : "");
        if (dough.isPresent()) {
            if (ingredient.isPresent()) {
                doughIngredientRepository.deleteByDoughAndIngredient(dough.get(), ingredient.get());
                log.info("DoughIngredientMapper - deleteByDoughAndIngredient() -> deleted!");
                return "deleted";
            }
            log.warn("DoughIngredientMapper - deleteByDoughAndIngredient() -> ingredient is null!");
            return "no deleted!";
        }
        log.warn("DoughIngredientMapper - deleteByDoughAndIngredient() -> dough is null!");
        return "no deleted!";
    }
}
