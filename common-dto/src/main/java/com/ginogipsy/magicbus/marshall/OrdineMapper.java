package com.ginogipsy.magicbus.marshall;

import com.ginogipsy.magicbus.domain.Ordine;
import com.ginogipsy.magicbus.dto.OrdineDTO;
import com.ginogipsy.magicbus.repository.OrdineRepository;
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
