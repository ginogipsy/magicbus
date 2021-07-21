package com.ginogipsy.magicbusV2.marshall;

import com.ginogipsy.magicbusV2.domain.Bibita;
import com.ginogipsy.magicbusV2.domain.Ordine;
import com.ginogipsy.magicbusV2.dto.BibitaDTO;
import com.ginogipsy.magicbusV2.dto.OrdineDTO;
import com.ginogipsy.magicbusV2.repository.OrdineRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class OrdineMapper {

    private final ModelMapper modelMapper;
    private final OrdineRepository ordineRepository;

    public OrdineMapper(ModelMapper modelMapper, OrdineRepository ordineRepository) {
        this.modelMapper = modelMapper;
        this.ordineRepository = ordineRepository;
    }

    public Ordine convertToEntity(OrdineDTO ordineDTO){
        return (ordineDTO != null) ? modelMapper.map(ordineDTO, Ordine.class) : null;
    }

    public OrdineDTO convertToDTO(Ordine ordine){
        return (ordine != null) ? modelMapper.map(ordine, OrdineDTO.class) : null;
    }
}
