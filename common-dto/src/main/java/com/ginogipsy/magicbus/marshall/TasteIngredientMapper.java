package com.ginogipsy.magicbus.marshall;


import com.ginogipsy.magicbus.domain.TasteIngredient;
import com.ginogipsy.magicbus.dto.TasteIngredientDTO;
import com.ginogipsy.magicbus.repository.TasteIngredientRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class TasteIngredientMapper {

    private final ModelMapper modelMapper;
    private final TasteIngredientRepository tasteIngredientRepository;

    public TasteIngredientMapper(ModelMapper modelMapper, TasteIngredientRepository tasteIngredientRepository) {
        this.modelMapper = modelMapper;
        this.tasteIngredientRepository = tasteIngredientRepository;
    }

    public TasteIngredient convertToEntity(TasteIngredientDTO tasteIngredientDTO){
        return (tasteIngredientDTO != null) ? modelMapper.map(tasteIngredientDTO, TasteIngredient.class) : null;
    }

    public TasteIngredientDTO convertToDTO(TasteIngredient tasteIngredient){
        return (tasteIngredient != null) ? modelMapper.map(tasteIngredient, TasteIngredientDTO.class) : null;
    }
}
