package com.ginogipsy.magicbusV2.marshall;

import com.ginogipsy.magicbusV2.domain.Bibita;
import com.ginogipsy.magicbusV2.domain.GustoUtente;
import com.ginogipsy.magicbusV2.dto.BibitaDTO;
import com.ginogipsy.magicbusV2.dto.GustoUtenteDTO;
import com.ginogipsy.magicbusV2.repository.GustoUtenteRepository;
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
