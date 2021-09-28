package com.ginogipsy.magicbus.marshall;

import com.ginogipsy.magicbus.domain.GustoUtente;
import com.ginogipsy.magicbus.dto.GustoUtenteDTO;
import com.ginogipsy.magicbus.repository.GustoUtenteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class GustoUtenteMapper {

    private final ModelMapper modelMapper;
    private final GustoUtenteRepository gustoUtenteRepository;

    public GustoUtenteMapper(ModelMapper modelMapper, GustoUtenteRepository gustoUtenteRepository) {
        this.modelMapper = modelMapper;
        this.gustoUtenteRepository = gustoUtenteRepository;
    }

    public GustoUtente convertToEntity(GustoUtenteDTO gustoUtenteDTO){
        return (gustoUtenteDTO != null) ? modelMapper.map(gustoUtenteDTO, GustoUtente.class) : null;
    }

    public GustoUtenteDTO convertToDTO(GustoUtente gustoUtente){
        return (gustoUtente != null) ? modelMapper.map(gustoUtente, GustoUtenteDTO.class) : null;
    }
}
