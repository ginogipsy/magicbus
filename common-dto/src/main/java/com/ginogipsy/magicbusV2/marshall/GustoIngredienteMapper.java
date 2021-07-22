package com.ginogipsy.magicbusV2.marshall;


import com.ginogipsy.magicbusV2.domain.GustoIngrediente;
import com.ginogipsy.magicbusV2.dto.GustoIngredienteDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class GustoIngredienteMapper {

    private final ModelMapper modelMapper;
    private final GustoIngredienteMapper ingredienteMapper;

    public GustoIngredienteMapper(ModelMapper modelMapper, GustoIngredienteMapper ingredienteMapper) {
        this.modelMapper = modelMapper;
        this.ingredienteMapper = ingredienteMapper;
    }

    public GustoIngrediente convertToEntity(GustoIngredienteDTO gustoIngredienteDTO){
        return (gustoIngredienteDTO != null) ? modelMapper.map(gustoIngredienteDTO, GustoIngrediente.class) : null;
    }

    public GustoIngredienteDTO convertToDTO(GustoIngrediente gustoIngrediente){
        return (gustoIngrediente != null) ? modelMapper.map(gustoIngrediente, GustoIngredienteDTO.class) : null;
    }
}
