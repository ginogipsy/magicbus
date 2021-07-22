package com.ginogipsy.magicbusV2.marshall;

import com.ginogipsy.magicbusV2.domain.Bibita;
import com.ginogipsy.magicbusV2.domain.Birra;
import com.ginogipsy.magicbusV2.dto.BibitaDTO;
import com.ginogipsy.magicbusV2.dto.BirraDTO;
import com.ginogipsy.magicbusV2.repository.BirraRepository;
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
