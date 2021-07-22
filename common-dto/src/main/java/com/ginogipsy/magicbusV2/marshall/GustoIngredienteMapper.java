package com.ginogipsy.magicbusV2.marshall;


import com.ginogipsy.magicbusV2.domain.GustoIngrediente;
import com.ginogipsy.magicbusV2.dto.GustoIngredienteDTO;
import com.ginogipsy.magicbusV2.repository.GustoIngredienteRepository;
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

    public GustoIngrediente convertToEntity(GustoIngredienteDTO gustoIngredienteDTO){
        return (gustoIngredienteDTO != null) ? modelMapper.map(gustoIngredienteDTO, GustoIngrediente.class) : null;
    }

    public GustoIngredienteDTO convertToDTO(GustoIngrediente gustoIngrediente){
        return (gustoIngrediente != null) ? modelMapper.map(gustoIngrediente, GustoIngredienteDTO.class) : null;
    }
}
