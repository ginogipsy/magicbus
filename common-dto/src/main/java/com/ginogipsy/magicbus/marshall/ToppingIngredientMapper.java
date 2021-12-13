package com.ginogipsy.magicbus.marshall;


import com.ginogipsy.magicbus.domain.ToppingIngredient;
import com.ginogipsy.magicbus.dto.ToppingIngredientDTO;
import com.ginogipsy.magicbus.repository.TasteIngredientRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ToppingIngredientMapper {

    private final ModelMapper modelMapper;
    private final TasteIngredientRepository tasteIngredientRepository;

    public ToppingIngredientMapper(ModelMapper modelMapper, TasteIngredientRepository tasteIngredientRepository) {
        this.modelMapper = modelMapper;
        this.tasteIngredientRepository = tasteIngredientRepository;
    }

    public ToppingIngredient convertToEntity(ToppingIngredientDTO toppingIngredientDTO){
        return (toppingIngredientDTO != null) ? modelMapper.map(toppingIngredientDTO, ToppingIngredient.class) : null;
    }

    public ToppingIngredientDTO convertToDTO(ToppingIngredient toppingIngredient){
        return (toppingIngredient != null) ? modelMapper.map(toppingIngredient, ToppingIngredientDTO.class) : null;
    }
}
