package com.ginogipsy.magicbus.marshall;


import com.ginogipsy.magicbus.domain.Birrificio;
import com.ginogipsy.magicbus.dto.BirrificioDTO;
import com.ginogipsy.magicbus.repository.BirrificioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class BirrificioMapper {

    private final ModelMapper modelMapper;
    private final BirrificioRepository birrificioRepository;

    public BirrificioMapper(ModelMapper modelMapper, BirrificioRepository birrificioRepository) {
        this.modelMapper = modelMapper;
        this.birrificioRepository = birrificioRepository;
    }

    public Birrificio convertToEntity(BirrificioDTO birrificioDTO){
        return (birrificioDTO != null) ? modelMapper.map(birrificioDTO, Birrificio.class) : null;
    }

    public BirrificioDTO convertToDTO(Birrificio birrificio){
        return (birrificio != null) ? modelMapper.map(birrificio, BirrificioDTO.class) : null;
    }
}