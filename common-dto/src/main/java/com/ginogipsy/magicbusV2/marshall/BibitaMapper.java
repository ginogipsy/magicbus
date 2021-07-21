package com.ginogipsy.magicbusV2.marshall;

import com.ginogipsy.magicbusV2.domain.Bibita;
import com.ginogipsy.magicbusV2.dto.BibitaDTO;
import com.ginogipsy.magicbusV2.repository.BibitaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class BibitaMapper {

    private final ModelMapper modelMapper;
    private final BibitaRepository bibitaRepository;

    public BibitaMapper(ModelMapper modelMapper, BibitaRepository bibitaRepository) {
        this.modelMapper = modelMapper;
        this.bibitaRepository = bibitaRepository;
    }

    public Bibita convertToEntity(BibitaDTO bibitaDTO){
        return (bibitaDTO != null) ? modelMapper.map(bibitaDTO, Bibita.class) : null;
    }

    public BibitaDTO convertToDTO(Bibita bibita){
        return (bibita != null) ? modelMapper.map(bibita, BibitaDTO.class) : null;
    }
}
