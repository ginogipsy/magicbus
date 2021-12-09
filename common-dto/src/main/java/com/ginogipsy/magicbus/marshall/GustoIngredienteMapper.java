package com.ginogipsy.magicbus.marshall;


import com.ginogipsy.magicbus.domain.TasteIngredient;
import com.ginogipsy.magicbus.dto.GustoIngredienteDTO;
import com.ginogipsy.magicbus.repository.GustoIngredienteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class GustoIngredienteMapper {

    private final ModelMapper modelMapper;
    private final GustoIngredienteRepository gustoIngredienteRepository;

    public GustoIngredienteMapper(ModelMapper modelMapper, GustoIngredienteRepository gustoIngredienteRepository) {
        this.modelMapper = modelMapper;
        this.gustoIngredienteRepository = gustoIngredienteRepository;
    }

    public TasteIngredient convertToEntity(GustoIngredienteDTO gustoIngredienteDTO){
        return (gustoIngredienteDTO != null) ? modelMapper.map(gustoIngredienteDTO, TasteIngredient.class) : null;
    }

    public GustoIngredienteDTO convertToDTO(TasteIngredient tasteIngredient){
        return (tasteIngredient != null) ? modelMapper.map(tasteIngredient, GustoIngredienteDTO.class) : null;
    }
}
