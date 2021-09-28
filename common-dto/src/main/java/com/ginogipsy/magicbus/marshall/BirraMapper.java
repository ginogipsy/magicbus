package com.ginogipsy.magicbus.marshall;

import com.ginogipsy.magicbus.domain.Birra;
import com.ginogipsy.magicbus.dto.BibitaDTO;
import com.ginogipsy.magicbus.dto.BirraDTO;
import com.ginogipsy.magicbus.repository.BirraRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class BirraMapper {

    private final ModelMapper modelMapper;
    private final BirraRepository birraRepository;

    public BirraMapper(ModelMapper modelMapper, BirraRepository birraRepository) {
        this.modelMapper = modelMapper;
        this.birraRepository = birraRepository;
    }

    public Birra convertToEntity(BirraDTO birraDTO){
        return (birraDTO != null) ? modelMapper.map(birraDTO, Birra.class) : null;
    }

    public BibitaDTO convertToDTO(Birra birra){
        return (birra != null) ? modelMapper.map(birra, BibitaDTO.class) : null;
    }
}
