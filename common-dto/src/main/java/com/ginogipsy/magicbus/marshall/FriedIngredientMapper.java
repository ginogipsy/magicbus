package com.ginogipsy.magicbus.marshall;

import com.ginogipsy.magicbus.domain.Fried;
import com.ginogipsy.magicbus.domain.FriedIngredient;
import com.ginogipsy.magicbus.domain.Ingredient;
import com.ginogipsy.magicbus.dto.FriedDTO;
import com.ginogipsy.magicbus.dto.FriedIngredientDTO;
import com.ginogipsy.magicbus.dto.IngredientDTO;
import com.ginogipsy.magicbus.repository.FriedIngredientRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Component
public class FriedIngredientMapper {

    private final ModelMapper modelMapper;
    private final FriedIngredientRepository friedIngredientRepository;
    private final IngredientMapper ingredientMapper;
    private final FriedMapper friedMapper;

    public FriedIngredientMapper(ModelMapper modelMapper, FriedIngredientRepository friedIngredientRepository, IngredientMapper ingredientMapper, FriedMapper friedMapper) {
        this.modelMapper = modelMapper;
        this.friedIngredientRepository = friedIngredientRepository;
        this.ingredientMapper = ingredientMapper;
        this.friedMapper = friedMapper;
    }

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

    public FriedIngredientDTO findByFriedAndIngredient(final FriedDTO friedDTO, final IngredientDTO ingredientDTO) {
        final Fried fried = takeFried(friedDTO);
        final Ingredient ingredient = takeIngredient(ingredientDTO);

        return Optional.ofNullable(fried)
                .filter(f -> Objects.nonNull(ingredient))
                .map(f -> friedIngredientRepository.findByFriedAndIngredient(f, ingredient))
                .map(this::convertToDTO)
                .orElse(null);
    }

    public FriedIngredientDTO save(final FriedIngredientDTO friedIngredientDTO) {
        log.info("Saving friedIngredient on db..");
        return Optional.ofNullable(friedIngredientDTO)
                .map(this::convertToEntity)
                .map(friedIngredientRepository::save)
                .map(this::convertToDTO)
                .orElse(null);
    }

    public List<FriedIngredientDTO> findByFried(final FriedDTO friedDTO) {
        final Fried fried = takeFried(friedDTO);
        log.info("Searching friedIngredient on db by dough..");
        return Optional.ofNullable(fried)
                .map(f -> friedIngredientRepository.findByFried(f)
                        .stream()
                        .map(this::convertToDTO)
                        .toList())
                .orElse(new ArrayList<>());
    }

    public List<FriedIngredientDTO> findByIngredient(final IngredientDTO ingredientDTO) {
        final Ingredient ingredient = takeIngredient(ingredientDTO);
        log.info("Searching friedIngredient on db by ingredient..");
        return Optional.ofNullable(ingredient)
                .map(i -> friedIngredientRepository.findByIngredient(i)
                        .stream()
                        .map(this::convertToDTO)
                        .toList())
                .orElse(new ArrayList<>());
    }

    private Fried takeFried(final FriedDTO friedDTO) {
        log.info("Verifying friedDTO..");
        return Optional.ofNullable(friedDTO)
                .map(friedMapper::convertToEntity)
                .orElse(null);
    }

    private Ingredient takeIngredient(final IngredientDTO ingredientDTO) {
        log.info("Verifying ingredientDTO..");
        return Optional.ofNullable(ingredientDTO)
                .map(ingredientMapper::convertToEntity)
                .orElse(null);
    }
}
